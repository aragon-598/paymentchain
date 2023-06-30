package com.paymentchain.transsaction.repository;

import com.paymentchain.transsaction.entities.Transaction;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByAccountIban(String accountIban);

    Transaction findByReference(String reference);

    boolean existsByAccountIban(String accountIban);

}
