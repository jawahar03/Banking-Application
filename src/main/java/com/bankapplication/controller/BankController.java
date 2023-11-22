package com.bankapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankapplication.config.ResponseStructure;
import com.bankapplication.dto.Bank;
import com.bankapplication.service.BankService;

@RestController
@RequestMapping("/bank")
public class BankController 
{
	@Autowired
	BankService ser;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Bank>> saveBank(@RequestBody Bank b)
	{
		return ser.saveBank(b);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Bank>> findBank(@RequestParam int id)
	{
		return ser.findBank(id);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Bank>> updateBank(@RequestParam int id, @RequestBody Bank b)
	{
		return ser.updateBank(id, b);
	}
	
	@PutMapping("/assign")
	public ResponseEntity<ResponseStructure<Bank>> assignBranch(@RequestParam int branchId , @RequestParam int bankId)
	{
		return ser.setBranchToBank(branchId, bankId);
	}
}
