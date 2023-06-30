package com.paymentchain.transsaction.serviceImp;

import com.paymentchain.transsaction.entities.Transaction;
import com.paymentchain.transsaction.repository.TransactionRepository;
import com.paymentchain.transsaction.service.TransactionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TransactionServiceImp implements TransactionService {

    @Autowired
    TransactionRepository repository;

    @Override
    public List<Transaction> getAllTransactions() {
        return repository.findAll();
    }

    @Override
    public List<Transaction> getTransactionsByAccountIban(String accountIban) {
        return repository.findAllByAccountIban(accountIban);
    }

    @Override
    public Transaction getTransactionById(long id) {
        return repository.findById(id).get();
    }

    @Override
    public Transaction getTransactionByReference(String reference) {
        return repository.findByReference(reference);
    }

    @Override
    public void saveTransaction(Transaction transaction) {
        repository.save(transaction);
    }

    @Override
    public void deleteTransactionById(long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsById(long id) {
        return repository.existsById(id);
    }

    @Override
    public boolean existsByAccountIban(String accountIban) {
        return repository.existsByAccountIban(accountIban);
    }
}
