package com.bankapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bankapplication.config.ResponseStructure;
import com.bankapplication.dao.BranchDao;
import com.bankapplication.dao.ManagerDao;
import com.bankapplication.dto.Branch;
import com.bankapplication.dto.Manager;

@Service
public class ManagerService
{
	@Autowired
	ManagerDao dao;
	@Autowired
	BranchDao bDao;
	
	public ResponseEntity<ResponseStructure<Manager>> saveManager(Manager manager , int id)
	{
		Branch branch = bDao.findBranch(id);
		Manager m = dao.saveManager(manager);
		branch.setManager(m);
		m.setBranch(branch);
		dao.updateManager(m.getManagerId(), m);
		ResponseStructure<Manager> res = new ResponseStructure<>();
		res.setData(m);
		res.setMsg("Manager has been saved");
		res.setStatus(HttpStatus.CREATED.value());
		
		return new ResponseEntity<ResponseStructure<Manager>>(res, HttpStatus.CREATED);
	}
}
