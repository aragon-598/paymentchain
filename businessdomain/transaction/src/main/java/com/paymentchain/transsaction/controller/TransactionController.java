package com.paymentchain.transsaction.controller;

import java.util.List;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paymentchain.transsaction.entities.Transaction;
import com.paymentchain.transsaction.service.TransactionService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService service;

    @Value("${user.alias}")
    private String alias;

    @GetMapping(value="/all")
    public ResponseEntity<?> findAllTransactions() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "ALIAS ====>"+alias);

        List<Transaction> allTransactions = service.getAllTransactions();

        if (!allTransactions.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(service.getAllTransactions());
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(service.getAllTransactions());
        }
    }
    

    @GetMapping(value="/customer/transactions")
    public ResponseEntity<?> findAllTransactionsByAccountIban(@RequestParam String accountIban) {
        List<Transaction> transactionsByAccount = service.getTransactionsByAccountIban(accountIban);
        if (!transactionsByAccount.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(service.getTransactionsByAccountIban(accountIban));
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(service.getTransactionsByAccountIban(accountIban));
        }
    }

    @GetMapping(value="/{reference}")
    public ResponseEntity<?> findTransactionByReference(@PathVariable String reference) {
        Transaction transaction = service.getTransactionByReference(reference);

        if (transaction!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(service.getTransactionByReference(reference));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro la transacción");
        }
    }

    @PostMapping(value="/")
    public ResponseEntity<?> saveTransaction(@RequestBody Transaction transaction) {
        service.saveTransaction(transaction);
        return ResponseEntity.ok("Created");
    }
    
    @PutMapping(value="/{id}")
    public ResponseEntity<?> updateTransaction(@PathVariable long id, @RequestBody Transaction transaction) {
        Transaction oldTransaction = service.getTransactionById(id);

        if (oldTransaction!=null) {
            transaction.setIdTransaction(id);
            service.saveTransaction(transaction);
            return ResponseEntity.status(HttpStatus.OK).body("Updated");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La transaccion no se actualizo porque no se encontró");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable("id") long id) {
        boolean exists = service.existsById(id);

        if (exists) {
            service.deleteTransactionById(id);
            return ResponseEntity.ok("Deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        }
    }
    
}
