package com.bankapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bankapplication.config.ResponseStructure;
import com.bankapplication.dao.AccountDao;
import com.bankapplication.dto.Account;
import com.bankapplication.dto.AccountType;
import com.bankapplication.dto.User;
import com.bankapplication.exception.AccountNotFound;
import com.bankapplication.exception.InvalidAccountType;

@Service
public class AccountService {
	@Autowired
	AccountDao adao;

	public ResponseEntity<ResponseStructure<User>> updateAccount(int aId, Account a) {
		ResponseStructure<User> res = new ResponseStructure<>();
		Account exAcc = adao.findAccount(aId);
		if (exAcc != null) {
			exAcc.setAccountNumber(a.getAccountNumber());
			exAcc.setBalance(a.getBalance());
			exAcc.setPassword(a.getPassword());
			Account updatedAcc = adao.updateAccount(aId, exAcc);
			res.setData(updatedAcc.getUser());
			res.setMsg("Account has been added to user");
			res.setStatus(HttpStatus.CREATED.value());

			return new ResponseEntity<ResponseStructure<User>>(res, HttpStatus.CREATED);
		} else {
			throw new AccountNotFound("Account Not Found In The Given Id");
		}
	}

	public ResponseEntity<ResponseStructure<Account>> changeAccountType(int aid, int aType) {
		ResponseStructure<Account> res = new ResponseStructure<>();
		Account acc = adao.findAccount(aid);
		if (acc != null) {
			if (aType == 1 && acc.getType() != AccountType.SAVINGS) {
				acc.setType(AccountType.SAVINGS);
				Account savedAcc = adao.updateAccount(aid, acc);
				res.setData(savedAcc);
				res.setMsg("Account type has been changed to savings");
				res.setStatus(HttpStatus.CREATED.value());
				return new ResponseEntity<ResponseStructure<Account>>(res, HttpStatus.CREATED);
			} else if (aType == 2 && acc.getType() != AccountType.CURRENT) {
				acc.setType(AccountType.CURRENT);
				Account savedAcc = adao.updateAccount(aid, acc);
				res.setData(savedAcc);
				res.setMsg("Account type has been changed to current");
				res.setStatus(HttpStatus.CREATED.value());
				return new ResponseEntity<ResponseStructure<Account>>(res, HttpStatus.CREATED);
			} else {
				throw new InvalidAccountType("Invalid Account Type");
			}
		} else {
			throw new AccountNotFound("Account Not Found In The Given Id");		
		}
	}
	
	public ResponseEntity<ResponseStructure<Account>> findbyAccountNumber(int num)
	{
		ResponseStructure<Account> res = new ResponseStructure<>();
		if(adao.findByAccountNumber(num)!=null)
		{
			res.setData(adao.findByAccountNumber(num));
			res.setMsg("Account found");
			res.setStatus(HttpStatus.FOUND.value());
			
			return new ResponseEntity<ResponseStructure<Account>>(res,HttpStatus.FOUND);
		}
		else {
			throw new AccountNotFound("Account Not Found In The Given Id");
		}
	}
	
}
