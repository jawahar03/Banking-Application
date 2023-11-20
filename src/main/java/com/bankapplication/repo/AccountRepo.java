package com.bankapplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankapplication.dto.Account;

public interface AccountRepo extends JpaRepository<Account, Integer>{

}
