package com.paymentchain.customer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import com.paymentchain.customer.entities.Customer;

//@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
    Optional<Customer> findByCode(String code);
}
