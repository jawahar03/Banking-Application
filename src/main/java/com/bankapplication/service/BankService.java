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
import com.bankapplication.exception.BankNotFound;
import com.bankapplication.exception.BranchNotFound;
import com.bankapplication.exception.UserNotFoundException;

@Service
public class BankService {
	@Autowired
	BankDao dao;

	@Autowired
	BranchDao bDao;

	public ResponseEntity<ResponseStructure<Bank>> saveBank(Bank k) {
		ResponseStructure<Bank> str = new ResponseStructure<>();
		str.setData(dao.saveBank(k));
		str.setMsg("Bank has been saved");
		str.setStatus(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Bank>>(str, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Bank>> findBank(int id) {
		ResponseStructure<Bank> str = new ResponseStructure<>();
		if (dao.findBank(id) != null) {
			str.setData(dao.findBank(id));
			str.setStatus(HttpStatus.CREATED.value());
			str.setMsg("Bank with id::" + id + " is found");
			return new ResponseEntity<ResponseStructure<Bank>>(str, HttpStatus.FOUND);
		}
		throw new BankNotFound("Bank Not Found In The Given Id");
	}

	public ResponseEntity<ResponseStructure<Bank>> updateBank(int id, Bank bank) {
		ResponseStructure<Bank> str = new ResponseStructure<>();
		if (dao.findBank(id) != null) {
			str.setData(dao.updateBank(id, bank));
			str.setStatus(HttpStatus.CREATED.value());
			str.setMsg("Bank with id::" + id + " is updated");
			return new ResponseEntity<ResponseStructure<Bank>>(str, HttpStatus.FOUND);
		}
		throw new BankNotFound("Bank Not Found In The Given Id");

	}

	public ResponseEntity<ResponseStructure<Bank>> setBranchToBank(int branchId, int bankId) {
		ResponseStructure<Bank> res = new ResponseStructure<>();
		if (dao.findBank(bankId) != null) 
		{
			Bank bank = dao.findBank(bankId);
			if (bDao.findBranch(branchId) != null) 
			{
				Branch branch = bDao.findBranch(branchId);
				List<Branch> branches = new ArrayList<>();
				branches.add(branch);
				bank.setBranch(branches);
				res.setData(dao.updateBank(bankId, bank));
				res.setMsg("Branch with id" + branchId + " is added to bank");
				res.setStatus(HttpStatus.CREATED.value());
				return new ResponseEntity<ResponseStructure<Bank>>(res, HttpStatus.CREATED);
				
			} else {
				throw new BranchNotFound("Branch Not Found In The Given Id");
			}
		} else {
			throw new BankNotFound("Bank Not Found In The Given Id");
		}
	}

}
