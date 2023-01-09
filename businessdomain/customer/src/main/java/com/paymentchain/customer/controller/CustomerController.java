package com.paymentchain.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paymentchain.customer.entities.Customer;
import com.paymentchain.customer.entities.CustomerProduct;
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

    @GetMapping(value="/")
    public ResponseEntity<?> findAllCustomers() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllCustomers());
    }
    
    @GetMapping(value="/{id}")
    public ResponseEntity<?> findCustomerById(@PathVariable("id") long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getCustomerById(id));
    }

    @GetMapping(value="/bycode")
    public ResponseEntity<?> findCustomerByCode(@RequestParam String code) {
        Customer customer =service.getCustomerByCode(code);
        List<CustomerProduct> products = customer.getProducts();
        products.forEach(p -> {
            String productName = service.getProductNameById(p.getProductId());
            p.setProductName(productName);
        });

        List<?> transactions = service.getTransactionsByIban(customer.getIban());
        customer.setTransactions(transactions);

        return ResponseEntity.status(HttpStatus.OK).body(customer);
    
    }
    @PostMapping(value="/")
    public ResponseEntity<?> saveCustomer(@RequestBody Customer customer) {
        customer.getProducts().forEach(p -> p.setCustomer(customer));
        service.saveCustomer(customer);
        return ResponseEntity.status(HttpStatus.OK).body("Created");
    }
    
    @PutMapping(value="/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable("id") long id, @RequestBody Customer customer) {
        customer.setCustomerId(id);
        service.saveCustomer(customer);
        return ResponseEntity.status(HttpStatus.OK).body("Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id")long id) {
        service.deleteCustomerById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    }
    
}
