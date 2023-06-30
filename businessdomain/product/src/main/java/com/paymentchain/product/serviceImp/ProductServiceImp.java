package com.paymentchain.product.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paymentchain.product.entities.Product;
import com.paymentchain.product.repository.ProductRepository;
import com.paymentchain.product.service.ProductService;

@Service
@Transactional
public class ProductServiceImp implements ProductService {

    @Autowired
    ProductRepository repository;

    @Override
    public List<Product> getAllProducts() {
        
        return repository.findAll();
    }

    @Override
    public Product getProductById(long id) {
        return repository.findById(id).get();
    }

    @Override
    public void saveProduct(Product product) {
        repository.save(product);
    }

    @Override
    public void deleteProductById(long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existById(long id) {
        return repository.existsById(id);
    }
    
}
