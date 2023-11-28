package com.bankapplication.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bankapplication.config.ResponseStructure;
import com.bankapplication.dao.AccountDao;
import com.bankapplication.dao.TransactionDao;
import com.bankapplication.dao.UserDao;
import com.bankapplication.dto.Account;
import com.bankapplication.dto.Transaction;
import com.bankapplication.dto.TransactionStatus;
import com.bankapplication.dto.TransactionType;
import com.bankapplication.dto.User;

@Service
public class TransactionService {
	@Autowired
	TransactionDao dao;
	@Autowired
	AccountDao adao;
	@Autowired
	UserDao udao;

	public ResponseEntity<ResponseStructure<Transaction>> sendMoney(int fromAccNum, String password, int toAccNum,
			double amount) {
		ResponseStructure<Transaction> res = new ResponseStructure<>();

		Account fromAccount = adao.findByAccountNumber(fromAccNum);
		if (fromAccount != null) {
			if (fromAccount.getPassword().equals(password)) {
				Account toAccount = adao.findByAccountNumber(toAccNum);
				if (toAccount != null) {
					if (fromAccount.getBalance() > amount) {
						if (amount >= 1) {
							fromAccount.setBalance(fromAccount.getBalance() - amount);
							toAccount.setBalance(toAccount.getBalance() + amount);
							Transaction t = new Transaction();
							t.setAmount(amount);
							t.setDate(LocalDate.now());
							t.setStatus(TransactionStatus.SUCCESS);
							t.setToAccount(toAccNum);
							t.setType(TransactionType.DEBITED);

							Transaction t2 = new Transaction();
							t2.setAmount(amount);
							t2.setDate(LocalDate.now());
							t2.setStatus(TransactionStatus.SUCCESS);
							t2.setToAccount(fromAccNum);
							t2.setType(TransactionType.CREDITED);
							toAccount.getTransaction().add(t2);

							dao.saveTransaction(t);
							fromAccount.getTransaction().add(t);
							adao.updateAccount(fromAccount.getAccountId(), fromAccount);
							adao.updateAccount(toAccount.getAccountId(), toAccount);

							res.setData(t);
							res.setMsg("Transaction successful");
							res.setStatus(HttpStatus.CREATED.value());

							return new ResponseEntity<ResponseStructure<Transaction>>(res, HttpStatus.CREATED);
						} else {
							return null; // invalid amount change the transaction status
						}
					} else {
						return null; // insufficient amount
					}
				} else {
					return null;// no account(to account) found exception
				}
			} else {
				return null; // forbidden
			}
		} else {
			return null; // no account(from account) found exception
		}
	}

	public ResponseEntity<ResponseStructure<List<Transaction>>> filterTransaction(String name, String password, int month) 
	{
		ResponseStructure<List<Transaction>> res = new ResponseStructure<>();

		User u = udao.findByName(name);
		if (u != null) {
			if (u.getAccount().getPassword().equals(password)) {
				Account a = u.getAccount();
				List<Transaction> tr = a.getTransaction();
				LocalDate currentDate = LocalDate.now();
				LocalDate preDate = currentDate.minusMonths(month);
				List<Transaction> filteredTransactions = new ArrayList<>();

				for (Transaction transaction : tr) {
					LocalDate transactionDate = transaction.getDate();

					if (transactionDate.isAfter(preDate) && transactionDate.isBefore(currentDate)) {
						filteredTransactions.add(transaction);
					}
					res.setData(filteredTransactions);
					res.setMsg("List of transaction found");
					res.setStatus(HttpStatus.FOUND.value());
				}
				return new ResponseEntity<ResponseStructure<List<Transaction>>>(res, HttpStatus.FOUND);

			} else {
				return null; // forbidden
			}

		} else {
			return null; // no user found
		}
	}
}
