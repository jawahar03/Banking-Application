package com.bankapplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankapplication.dto.Bank;

public interface BankRepo extends JpaRepository<Bank, Integer>{

}
