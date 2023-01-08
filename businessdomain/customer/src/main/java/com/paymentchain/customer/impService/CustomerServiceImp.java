package com.paymentchain.customer.impService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paymentchain.customer.entities.Customer;
import com.paymentchain.customer.repository.CustomerRepository;
import com.paymentchain.customer.service.CustomerService;

@Service
@Transactional
public class CustomerServiceImp implements CustomerService {

    @Autowired
    CustomerRepository repository;

    @Override
    public List<Customer> getAllCustomers() {
        // TODO Auto-generated method stub
        return repository.findAll();
    }

    @Override
    public Customer getCustomerById(long id) {
        // TODO Auto-generated method stub
        return repository.findById(id).get();
    }

    @Override
    public void saveCustomer(Customer customer) {
        // TODO Auto-generated method stub
        //Cambios
        repository.save(customer);
    }

    @Override
    public void deleteCustomerById(long id) {
        // TODO Auto-generated method stub
        repository.deleteById(id);
    }
    
}
