package com.paymentchain.customer.service;

import java.util.List;

import com.paymentchain.customer.entities.Customer;

public interface CustomerService {
    
    List<Customer> getAllCustomers();

    Customer getCustomerById(long id);

    void saveCustomer(Customer customer);

    void deleteCustomerById(long id);

    String getProductNameById(long idProduct);
}
