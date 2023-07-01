package com.paymentchain.customer.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paymentchain.customer.entities.Customer;
import com.paymentchain.customer.entities.CustomerProduct;
import com.paymentchain.customer.exception.BusinessRuleException;
import com.paymentchain.customer.service.CustomerService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService service;

    @Value("${spring.datasource.username}")
    private String usernameDB;

    @GetMapping(value="/")
    public ResponseEntity<?> findAllCustomers() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "USERNAME ====>"+usernameDB);
        
        List<Customer> allCustomers =service.getAllCustomers();

        if (!allCustomers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(service.getAllCustomers());
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(service.getAllCustomers());
        }

    }
    
    @GetMapping(value="/{id}")
    public ResponseEntity<?> findCustomerById(@PathVariable("id") long id) {
        boolean existe = service.existsById(id);

        if (existe) {
            return ResponseEntity.status(HttpStatus.OK).body(service.getCustomerById(id));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el customer");
        }
    }

    @GetMapping(value="/bycode")
    public ResponseEntity<?> findCustomerByCode(@RequestParam String code) {
        Customer customer =service.getCustomerByCode(code);

        if (customer!=null) {
            List<CustomerProduct> products = customer.getProducts();
            products.forEach(p -> {
                String productName = service.getProductNameById(p.getProductId());
                p.setProductName(productName);
            });

            List<?> transactions = service.getTransactionsByIban(customer.getIban());
            customer.setTransactions(transactions);

            return ResponseEntity.status(HttpStatus.OK).body(customer);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro al customer por el codigo");
        }
    
    }
    @PostMapping(value="/")
    public ResponseEntity<?> saveCustomer(@RequestBody Customer customer) throws BusinessRuleException {
        
        service.saveCustomer(customer);
        return ResponseEntity.status(HttpStatus.OK).body("Created");
    }
    
    @PutMapping(value="/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable("id") long id, @RequestBody Customer customer) throws BusinessRuleException {
        boolean existe = service.existsById(id);

        if (existe) {
            customer.setCustomerId(id);
            service.saveCustomer(customer);
            return ResponseEntity.status(HttpStatus.OK).body("Updated");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se actualizo el customer porque no existe");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id")long id) {
        boolean existe = service.existsById(id);

        if (existe) {
            service.deleteCustomerById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("No se elimino el usuario porque no existe");
        }
    }
    
}
