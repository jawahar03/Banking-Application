package com.bankapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankapplication.config.ResponseStructure;
import com.bankapplication.dto.Address;
import com.bankapplication.dto.Branch;
import com.bankapplication.dto.User;
import com.bankapplication.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	AddressService ser;
	
	@PostMapping("/uaddress")
	public ResponseEntity<ResponseStructure<User>> createAddressForUser(@RequestBody Address a , @RequestParam int id)
	{
		return ser.createAddressForUser(id, a);
	}
	
	@PostMapping("/baddress")
	public ResponseEntity<ResponseStructure<Branch>> createAddressForBranch(@RequestBody Address a , @RequestParam int id)
	{
		return ser.createAddressForBranch(id, a);
	}
	
	
}
