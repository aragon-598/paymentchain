package com.paymentchain.transsaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(value="/customer/transactions")
    public ResponseEntity<?> findAllTransactionsByAccountIban(@RequestParam String accountIban) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getTransactionsByAccountIban(accountIban));
    }

    @GetMapping(value="/{reference}")
    public ResponseEntity<?> findTransactionByReference(@PathVariable String reference) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getTransactionByReference(reference));
    }

    @PostMapping(value="/")
    public ResponseEntity<?> saveTransaction(@RequestBody Transaction transaction) {
        //TODO: process POST request
        service.saveTransaction(transaction);
        return ResponseEntity.ok("Created");
    }
    
    @PutMapping(value="/{id}")
    public ResponseEntity<?> updateTransaction(@PathVariable long id, @RequestBody Transaction transaction) {
        //TODO: process PUT request
        transaction.setIdTransaction(id);
        service.saveTransaction(transaction);
        return ResponseEntity.status(HttpStatus.OK).body("Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable("id") long id) {
        //TODO: process POST request
        service.deleteTransactionById(id);
        return ResponseEntity.ok("Deleted");
    }
    
}
