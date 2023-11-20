package com.bankapplication.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bankapplication.dto.Transaction;
import com.bankapplication.repo.TransactionRepo;

@Repository
public class TransactionDao 
{
	@Autowired
	TransactionRepo repo;
	
	public Transaction saveTransaction(Transaction a)
	{
		return repo.save(a);
	}
	
	public Transaction findTransaction(int id)
	{
		Optional<Transaction> acc = repo.findById(id);
		return acc.get() ;
	}
	
	public Transaction deleteTransaction(int id)
	{
		Transaction acc = findTransaction(id);
		if(acc != null)
		{
			repo.delete(acc);
			return acc;
		}
		return null;
	}
	public List<Transaction> findAllTransaction()
	{
		return repo.findAll();
	}
	public Transaction updateTransaction(int id, Transaction b) {
		Transaction ex = findTransaction(id);
		if(ex != null)
		{
			b.setTransactionId(id);
			return repo.save(b);
		}
		return null;
	}
}
