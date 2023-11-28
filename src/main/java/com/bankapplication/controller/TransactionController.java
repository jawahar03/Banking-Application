package com.bankapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankapplication.config.ResponseStructure;
import com.bankapplication.dto.Transaction;
import com.bankapplication.service.TransactionService;


@RestController
@RequestMapping("/transaction")
public class TransactionController 
{
	@Autowired
	TransactionService ser;
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Transaction>> sendMoney(@RequestParam int fromAccountNum,@RequestParam String password ,@RequestParam int toAccountNum, @RequestParam double amount)
	{
		return ser.sendMoney(fromAccountNum,password, toAccountNum, amount);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Transaction>>> filterTransaction(@RequestParam String name, @RequestParam String password, @RequestParam int month )
	{
		return ser.filterTransaction(name, password, month);
	}
}
