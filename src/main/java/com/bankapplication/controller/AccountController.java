package com.bankapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankapplication.config.ResponseStructure;
import com.bankapplication.dto.Account;
import com.bankapplication.dto.User;
import com.bankapplication.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController 
{
	@Autowired
	AccountService ser;
	
	@PutMapping
	public ResponseEntity<ResponseStructure<User>> addAccountToUser(@RequestParam int id, @RequestBody Account a)
	{
		return ser.updateAccount(id, a);
	}
	
	@PutMapping("/change")
	public ResponseEntity<ResponseStructure<Account>> changeAccountType(@RequestParam int id , @RequestParam int type)
	{
		return ser.changeAccountType(id, type);
	}
}
