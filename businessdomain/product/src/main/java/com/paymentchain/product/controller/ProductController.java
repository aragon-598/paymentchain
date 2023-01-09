package com.paymentchain.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.paymentchain.product.entities.Product;
import com.paymentchain.product.service.ProductService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService service;

    @GetMapping(value="/")
    public ResponseEntity<?> findAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllProducts());
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<?> findProductById(@PathVariable("id")long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getProductById(id));
    }

    @PostMapping(value="/")
    public ResponseEntity<?> saveProduct(@RequestBody Product product) {
        service.saveProduct(product);
        return ResponseEntity.status(HttpStatus.OK).body("Saved");
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
        product.setId(id);
        service.saveProduct(product);
        return ResponseEntity.status(HttpStatus.OK).body("Updated");
    }
    
    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable("id")long id) {
        service.deleteProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    }
    
    
}
