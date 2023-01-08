package com.paymentchain.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paymentchain.customer.entities.Customer;
import com.paymentchain.customer.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




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

    @PostMapping(value="/")
    public ResponseEntity<?> saveCustomer(@RequestBody Customer customer) {
        service.saveCustomer(customer);
        return ResponseEntity.status(HttpStatus.OK).body("Created");
    }
    
    
}
