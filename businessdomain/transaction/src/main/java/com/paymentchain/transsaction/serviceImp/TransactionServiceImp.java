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
        // TODO Auto-generated method stub
        return repository.findAll();
    }

    @Override
    public List<Transaction> getTransactionsByAccountIban(String accountIban) {
        // TODO Auto-generated method stub
        return repository.findAllByAccountIban(accountIban);
    }

    @Override
    public Transaction getTransactionById(long id) {
        // TODO Auto-generated method stub
        return repository.findById(id).get();
    }

    @Override
    public Transaction getTransactionByReference(String reference) {
        // TODO Auto-generated method stub
        return repository.findByReference(reference);
    }

    @Override
    public void saveTransaction(Transaction transaction) {
        // TODO Auto-generated method stub
        repository.save(transaction);
    }

    @Override
    public void deleteTransactionById(long id) {
        // TODO Auto-generated method stub
        repository.deleteById(id);
    }
}
