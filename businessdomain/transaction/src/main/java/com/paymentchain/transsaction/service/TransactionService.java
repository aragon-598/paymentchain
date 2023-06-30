package com.paymentchain.transsaction.service;

import java.util.List;

import com.paymentchain.transsaction.entities.Transaction;

public interface TransactionService{
    List<Transaction> getAllTransactions();
    
    List<Transaction> getTransactionsByAccountIban(String accountIban);

    Transaction getTransactionById(long id);

    Transaction getTransactionByReference(String reference);

    void saveTransaction(Transaction transaction);

    void deleteTransactionById(long id);

    boolean existsById(long id);

    boolean existsByAccountIban(String accountIban);
}
