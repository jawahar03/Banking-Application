package com.bankapplication.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bankapplication.dto.Bank;
import com.bankapplication.repo.BankRepo;

@Repository
public class BankDao 
{
	@Autowired
	BankRepo repo;
	
	public Bank saveBank(Bank a)
	{
		return repo.save(a);
	}
	
	public Bank findBank(int id)
	{
		Optional<Bank> acc = repo.findById(id);
		return acc.get() ;
	}
	
	public Bank deleteBank(int id)
	{
		Bank acc = findBank(id);
		if(acc != null)
		{
			repo.delete(acc);
			return acc;
		}
		return null;
	}
	public List<Bank> findAllBank()
	{
		return repo.findAll();
	}
	
	public Bank updateBank(int id, Bank b) {
		Bank ex = findBank(id);
		if(ex != null)
		{
			b.setBankId(id);
			return repo.save(b);
		}
		return null;
	}
}
