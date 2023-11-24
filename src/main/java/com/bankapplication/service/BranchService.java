package com.bankapplication.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bankapplication.config.ResponseStructure;
import com.bankapplication.dao.BankDao;
import com.bankapplication.dao.BranchDao;
import com.bankapplication.dto.Bank;
import com.bankapplication.dto.Branch;
import com.bankapplication.dto.Manager;

@Service
public class BranchService 
{
	@Autowired
	BranchDao dao;
	@Autowired
	BankDao bDao;
	
	public ResponseEntity<ResponseStructure<Branch>> saveBranch(Branch branch , int id)
	{
		ResponseStructure<Branch> res = new ResponseStructure<>();
		Bank exBank = bDao.findBank(id);
		Branch savedBranch = dao.saveBranch(branch);
		exBank.getBranch().add(savedBranch);
		savedBranch.setBank(exBank);
		dao.updateBranch(savedBranch.getBranchId(), savedBranch);
		
		res.setData(savedBranch);
		res.setMsg("Branch has been added");
		res.setStatus(HttpStatus.CREATED.value());
			
		return new ResponseEntity<ResponseStructure<Branch>>(res , HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Branch>> findBranch(int branchId)
	{
		if(dao.findBranch(branchId) != null)
		{
			ResponseStructure<Branch> res = new ResponseStructure<>();
			res.setData(dao.findBranch(branchId));
			res.setMsg("Branch with id:"+branchId+" is found");
			res.setStatus(HttpStatus.FOUND.value());
			
			return new ResponseEntity<ResponseStructure<Branch>>(res , HttpStatus.FOUND);
		}
		else {
			return null; //no branch found  exception
		}
	}
	
	public ResponseEntity<ResponseStructure<Branch>> deleteBranch(int id)
	{
		if(dao.findBranch(id)!= null)
		{
			ResponseStructure<Branch> res = new ResponseStructure<>();
			Branch exBranch = dao.findBranch(id);
			Bank bank = exBranch.getBank();
			Manager exMan = exBranch.getManager();
			
			exBranch.setBank(null);
			exBranch.setManager(null);
			exMan.setBranch(null);
			bank.getBranch().remove(exBranch);
			dao.updateBranch(id, exBranch);
			
			res.setData(dao.deleteBranch(id));
			res.setMsg("Branch with id:"+id+" has been deleted");
			res.setStatus(HttpStatus.CREATED.value());
			
			return new ResponseEntity<ResponseStructure<Branch>>(res, HttpStatus.CREATED);
		}
		else {
			return null; //Branch not found exception
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Branch>>> findAllBranch()
	{
		ResponseStructure<List<Branch>> res = new ResponseStructure<>();;
		res.setData(dao.findAllBranch());
		res.setMsg("All branch has been found");
		res.setStatus(HttpStatus.CREATED.value());
		
		return new ResponseEntity<ResponseStructure<List<Branch>>>(res, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Branch>> updateBranch(int id, Branch branch) {
		ResponseStructure<Branch> str = new ResponseStructure<>();
		if (dao.findBranch(id) != null) {
			str.setData(dao.updateBranch(id, branch));
			str.setStatus(HttpStatus.CREATED.value());
			str.setMsg("Branch with id::" + id + " is updated");
			return new ResponseEntity<ResponseStructure<Branch>>(str, HttpStatus.FOUND);
		}
		return null; // Branch not found exception

	}
	
	public ResponseEntity<ResponseStructure<Branch>> setBankToBranch(int branchId, int bankId) {
		ResponseStructure<Branch> res = new ResponseStructure<>();
		if (bDao.findBank(bankId) != null) 
		{
			Bank bank = bDao.findBank(bankId);
			if (dao.findBranch(branchId) != null) 
			{
				Branch branch = dao.findBranch(branchId);
				branch.setBank(bank);
				res.setData(dao.updateBranch(branchId, branch));
				res.setMsg("Bank with id" + bankId + " is added to bank");
				res.setStatus(HttpStatus.CREATED.value());
				return new ResponseEntity<ResponseStructure<Branch>>(res, HttpStatus.CREATED);
				
			} else {
				return null; // No branch Found Exception
			}
		} else {
			return null; // No bank found exception
		}
	}
	
}
