package com.bankapplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bankapplication.dto.Account;

public interface AccountRepo extends JpaRepository<Account, Integer>
{
	@Query("select a from Account a where a.accountNumber=?1")
	public Account findAccountByNumber(int accNo);
}
