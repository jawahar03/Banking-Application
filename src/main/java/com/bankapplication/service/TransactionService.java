package com.bankapplication.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bankapplication.config.ResponseStructure;
import com.bankapplication.dao.AccountDao;
import com.bankapplication.dao.TransactionDao;
import com.bankapplication.dto.Account;
import com.bankapplication.dto.Transaction;
import com.bankapplication.dto.TransactionStatus;
import com.bankapplication.dto.TransactionType;

@Service
public class TransactionService 	
{
	@Autowired
	TransactionDao dao;
	@Autowired
	AccountDao adao;
	
	public ResponseEntity<ResponseStructure<Transaction>> sendMoney(int fromAccNum ,String password, int toAccNum, double amount)
	{
		ResponseStructure<Transaction> res = new ResponseStructure<>();
		
		Account fromAccount = adao.findByAccountNumber(fromAccNum);
		if(fromAccount!=null)
		{
			if(fromAccount.getPassword().equals(password))
			{
				Account toAccount = adao.findByAccountNumber(toAccNum);
				if(toAccount!=null)
				{
					if(amount>=1)
					{
						fromAccount.setBalance(fromAccount.getBalance()-amount);
						toAccount.setBalance(toAccount.getBalance()+amount);
						Transaction t = new Transaction();
						t.setAmount(amount);
						t.setDate(LocalDate.now());
						t.setStatus(TransactionStatus.SUCCESS);
						t.setToAccount(toAccNum);
						t.setType(TransactionType.CREDITED);
						
						dao.saveTransaction(t);
						fromAccount.getTransaction().add(t);
						adao.updateAccount(fromAccount.getAccountId(),fromAccount);
						adao.updateAccount(toAccount.getAccountId(), toAccount);
						
						res.setData(t);
						res.setMsg("Transaction successful");
						res.setStatus(HttpStatus.CREATED.value());
						
						return new ResponseEntity<ResponseStructure<Transaction>>(res, HttpStatus.CREATED);
					}
					else {
						return null; // invalid amount
					}
				}
				else {
					return null;//no account(to account) found exception
				}
			}
			else {
				return null; // forbidden
			}
		}
		else {
			return null; //no account(from account) found exception
		}
	}
}
