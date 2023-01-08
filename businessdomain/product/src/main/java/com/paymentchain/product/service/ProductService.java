package com.paymentchain.product.service;

import java.util.List;

import com.paymentchain.product.entities.Product;

public interface ProductService {
    
    List<Product> getAllProducts();

    Product getProductById(long id);

    void saveProduct(Product product);

    void deleteProductById(long id);

}
