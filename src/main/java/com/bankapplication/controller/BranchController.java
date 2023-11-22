package com.bankapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankapplication.config.ResponseStructure;
import com.bankapplication.dto.Branch;
import com.bankapplication.service.BranchService;

@RestController
@RequestMapping("/branch")
public class BranchController 
{
	@Autowired
	BranchService ser;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Branch>> saveBranch(@RequestBody Branch branch , @RequestParam int id)
	{
		return ser.saveBranch(branch, id);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Branch>> FindBranch( @RequestParam int id)
	{
		return ser.findBranch(id);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Branch>> deleteBranch( @RequestParam int id)
	{
		return ser.deleteBranch(id);
	} 
	
	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<List<Branch>>> FindAllBranch()
	{
		return ser.findAllBranch();
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Branch>> updateBranch(@RequestParam int id, @RequestBody Branch branch)
	{
		return ser.updateBranch(id, branch);
	}
	
	@PutMapping("/assign")
	public ResponseEntity<ResponseStructure<Branch>> assignBank(@RequestParam int branchId, @RequestParam int bankId)
	{
		return ser.setBankToBranch(branchId, bankId);
	}
	
	
}
