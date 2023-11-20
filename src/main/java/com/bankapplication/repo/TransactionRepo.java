package com.bankapplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankapplication.dto.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Integer> {

}
