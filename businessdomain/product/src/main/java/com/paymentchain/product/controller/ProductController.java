package com.paymentchain.product.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${user.role}")
    private String role;

    @GetMapping(value="/")
    public ResponseEntity<?> findAllProducts() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "AQUI ESTA EL ROL DESDE CONFIG SERVER =====>"+role);
        List<Product> allProducts = service.getAllProducts();
        if (allProducts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(service.getAllProducts());
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(service.getAllProducts());
        }
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<?> findProductById(@PathVariable("id")long id) {
        
        Product productById = service.getProductById(id);
        
        if (productById!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(service.getProductById(id));   
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el producto");    
        }
    }

    @PostMapping(value="/")
    public ResponseEntity<?> saveProduct(@RequestBody Product product) {
        service.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body("Saved");
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
        
        boolean oldProductExists = service.existById(id);
        
        if (oldProductExists) {
            product.setId(id);
            service.saveProduct(product);
            return ResponseEntity.status(HttpStatus.OK).body("Producto actualizado");
        } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no se pudo actualizar porque no existe");    
        }
        
    }
    
    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable("id")long id) {
        boolean exists = service.existById(id);

        if (exists) {
            service.deleteProductById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no se pudo eliminar porque no existe");
        }
    }
    
    
}
