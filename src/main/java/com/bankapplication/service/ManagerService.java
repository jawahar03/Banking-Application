package com.bankapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bankapplication.config.ResponseStructure;
import com.bankapplication.dao.BranchDao;
import com.bankapplication.dao.ManagerDao;
import com.bankapplication.dao.UserDao;
import com.bankapplication.dto.Branch;
import com.bankapplication.dto.Manager;
import com.bankapplication.dto.User;
import com.bankapplication.exception.BranchNotFound;
import com.bankapplication.exception.IncorrectPassword;
import com.bankapplication.exception.ManagerNotFound;
import com.bankapplication.exception.UserAlreadyPresent;
import com.bankapplication.exception.UserNotFoundException;
import com.bankapplication.repo.ManagerRepo;

@Service
public class ManagerService
{
	@Autowired
	ManagerDao dao;
	@Autowired
	BranchDao bDao;
	@Autowired
	ManagerRepo repo;
	@Autowired
	UserDao udao;
	
	
	public ResponseEntity<ResponseStructure<Manager>> saveManager(Manager manager , int id)
	{
		Branch branch = bDao.findBranch(id);
		if(branch!=null)
		{
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
		else {
			throw new BranchNotFound("Branch Not Found In The Given Id");
		}
	}
	
	public ResponseEntity<ResponseStructure<Manager>> findManager(int id)
	{
		ResponseStructure<Manager> res = new ResponseStructure<>();
		if(dao.findManager(id)!=null)
		{
			res.setData(dao.findManager(id));
			res.setMsg("Manager found");
			res.setStatus(HttpStatus.CREATED.value());
			
			return new ResponseEntity<ResponseStructure<Manager>>(res , HttpStatus.CREATED);
		}
		else {
			throw new ManagerNotFound("Manager Not Found In The Given Id");
		}
	}
	
	public ResponseEntity<ResponseStructure<Manager>> deleteManager(int id)
	{
		ResponseStructure<Manager> res = new ResponseStructure<>();
		if(dao.findManager(id)!=null)
		{
			Manager exMan = dao.findManager(id);
			Branch branch = exMan.getBranch();
			
			exMan.setBranch(null);
			branch.setManager(null);
			dao.updateManager(id, exMan);
			
			res.setData(dao.deleteManager(id));
			res.setMsg("Manager with id"+id+" is deleted");
			res.setStatus(HttpStatus.CREATED.value());
			
			
			return new ResponseEntity<ResponseStructure<Manager>>(res , HttpStatus.CREATED);
		}
		else {
			throw new ManagerNotFound("Manager Not Found In The Given Id");
		}
	}
	
	public ResponseEntity<ResponseStructure<Manager>> updateManage(int id , Manager m)
	{
		ResponseStructure<Manager> res = new ResponseStructure<>();
		if(dao.findManager(id)!=null)
		{
			Manager manager = dao.findManager(id);
			m.setManagerId(manager.getManagerId());
			res.setData(dao.saveManager(manager));
			res.setMsg("Manager has been updated");
			res.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Manager>>(res , HttpStatus.CREATED);			
		}
		else {
			throw new ManagerNotFound("Manager Not Found In The Given Id");
		}
	}
	
	public ResponseEntity<ResponseStructure<Manager>> login(String name, String password)
	{
		ResponseStructure<Manager> res = new ResponseStructure<>();
		if(repo.findManager(name)!=null)
		{
			Manager manager = repo.findManager(name);
			if(manager.getPassword().equals(password))
			{
				res.setData(manager);
				res.setMsg("Loggeed in");
				res.setStatus(HttpStatus.CREATED.value());
				
				return new ResponseEntity<ResponseStructure<Manager>>(res,HttpStatus.CREATED);
			}
			else {
				throw new  IncorrectPassword("Incorrect Password");
			}
		}
		else {
			throw new ManagerNotFound("Manager Not Found In The Given Id");
		}
	}
	
	
	public ResponseEntity<ResponseStructure<User>> changeBranch(int uid, int bid, String mname, String mpassword)
	{
		ResponseStructure<User> res = new ResponseStructure<>();
		Manager m = dao.login(mname, mpassword);
		if(m!=null)
		{
			User u = udao.findUser(uid);
			if(u!=null)
			{
				Branch b = bDao.findBranch(bid);
				if(b!=null)
				{
					if(u.getBranch().getBranchId()!=bid)
					{
						Branch exBranch = u.getBranch();
						exBranch.getUser().remove(u);
						bDao.updateBranch(exBranch.getBranchId(), exBranch);
						u.setBranch(b);
						b.getUser().add(u);
						bDao.updateBranch(bid, b);
						res.setData(u);
						res.setMsg("User account moved to new branch");
						res.setStatus(HttpStatus.CREATED.value());
						return new ResponseEntity<ResponseStructure<User>>(res,HttpStatus.CREATED);
					}
					else {
						throw new UserAlreadyPresent("User Already Present In The Branch");
					}
				}
				else {
					throw new BranchNotFound("Branch Not Found In The Given Id");
				}
			}
			else {
				throw new UserNotFoundException("User Not Found In The Given Id");
			}
		}
		else {
			throw new ManagerNotFound("Manager Not Found In The Given Id");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
