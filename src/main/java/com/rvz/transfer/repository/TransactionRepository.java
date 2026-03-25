package com.rvz.transfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rvz.transfer.entity.Transaction;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByFromAccount(int fromAccount);
    List<Transaction> findByToAccount(int toAccount);
}
