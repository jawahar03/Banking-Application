package com.bankapplication.controller;
  
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankapplication.config.ResponseStructure;
import com.bankapplication.dto.User;
import com.bankapplication.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController 
{
	@Autowired
	UserService ser;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User u, @RequestParam int accType, @RequestParam String mName, @RequestParam String password )
	{
		return ser.saveUser(u, accType, mName, password);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<User>> findUser(@RequestParam int id)
	{
		return ser.findUser(id);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<User>> deleteUser(@RequestParam int id)
	{
		return ser.deleteUser(id);
	}
	
	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<List<User>>> findAllUser()
	{
		return ser.findAllUser();
	}
	
	@GetMapping("/login")
	public ResponseEntity<ResponseStructure<User>> userLogin(@RequestParam String name, @RequestParam String password)
	{
		return ser.userLogin(name, password);
	}
}
