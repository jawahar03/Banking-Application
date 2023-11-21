package com.bankapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bankapplication.config.ResponseStructure;
import com.bankapplication.dao.BankDao;
import com.bankapplication.dto.Bank;

@Service
public class BankService 
{
	@Autowired
	BankDao dao;
	
	public ResponseEntity<ResponseStructure<Bank>> saveBank(Bank k)
	{
		ResponseStructure<Bank> str = new ResponseStructure<>();
		str.setData(dao.saveBank(k));
		str.setMsg("Bank has been saved");
		str.setStatus(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Bank>>(str, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Bank>> findBank(int id)
	{
		ResponseStructure<Bank> str = new ResponseStructure<>();
		if(dao.findBank(id)!=null)
		{
			str.setData(dao.findBank(id));
			str.setStatus(HttpStatus.CREATED.value());
			str.setMsg("Bank with id::" + id+ " is found");
			return new ResponseEntity<ResponseStructure<Bank>>(str,HttpStatus.FOUND);
		}
		return null; //Bank not found exception
	}
	
	public ResponseEntity<ResponseStructure<Bank>> updateBank(int id, Bank bank)
	{
		ResponseStructure<Bank> str = new ResponseStructure<>();
		if(dao.findBank(id)!=null)
		{
			str.setData(dao.updateBank(id, bank));
			str.setStatus(HttpStatus.CREATED.value());
			str.setMsg("Bank with id::" + id+ " is updated");
			return new ResponseEntity<ResponseStructure<Bank>>(str,HttpStatus.FOUND);
		}
		return null; //Bank not found exception
		
	}
	
	
}
