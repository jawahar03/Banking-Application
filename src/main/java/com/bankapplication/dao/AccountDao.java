package com.bankapplication.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bankapplication.dto.Account;
import com.bankapplication.repo.AccountRepo;

@Repository
public class AccountDao 
{
	@Autowired
	AccountRepo repo;
	
	public Account saveAccount(Account a)
	{
		return repo.save(a);
	}
	
	public Account findAccount(int id)
	{
		Optional<Account> acc = repo.findById(id);
		return acc.get() ;
	}
	
	public Account deleteAccount(int id)
	{
		Account acc = findAccount(id);
		if(acc != null)
		{
			repo.delete(acc);
			return acc;
		}
		return null;
	}
	public List<Account> findAllAccount()
	{
		return repo.findAll();
	}
	public Account updateAccount(int id, Account b) {
		Account ex = findAccount(id);
		if(ex != null)
		{
			b.setAccountId(id);
			return repo.save(b);
		}
		return null;
	}
	
	
	
	public Account findByAccountNumber(int num)
	{
		return repo.findAccountByNumber(num);
	}
	
	
}
