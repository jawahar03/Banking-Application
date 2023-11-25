package com.bankapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bankapplication.config.ResponseStructure;
import com.bankapplication.dao.ManagerDao;
import com.bankapplication.dao.UserDao;
import com.bankapplication.dto.Account;
import com.bankapplication.dto.AccountType;
import com.bankapplication.dto.Branch;
import com.bankapplication.dto.Manager;
import com.bankapplication.dto.User;

@Service
public class UserService {
	@Autowired
	ManagerDao mdao;
	@Autowired
	UserDao udao;

	public ResponseEntity<ResponseStructure<User>> saveUser(User user, int accType, String name, String password) {
		ResponseStructure<User> res = new ResponseStructure<>();
		Manager manager = mdao.login(name, password);
		if (manager != null) {
			Branch b = manager.getBranch();
			Account a = new Account();
			if (accType == 1) {
				a.setType(AccountType.SAVINGS);
			} else if (accType == 2) {
				a.setType(AccountType.CURRENT);
			} else {
				return null; // account type mismatch exception
			}

			user.setAccount(a);
			user.setBranch(b);
			a.setUser(user);
			User savedUser = udao.saveUser(user);

			b.getUser().add(savedUser);
			mdao.updateManager(manager.getManagerId(), manager);
			res.setData(savedUser);
			res.setMsg("User has been saved");
			res.setStatus(HttpStatus.CREATED.value());

			return new ResponseEntity<ResponseStructure<User>>(res, HttpStatus.CREATED);

		} else {
			return null; // manager not found exception
		}
	}
	
	
	public ResponseEntity<ResponseStructure<User>> findUser(int id)
	{
		ResponseStructure<User> res = new ResponseStructure<>();
		if(udao.findUser(id)!=null)
		{
			res.setData(udao.findUser(id));
			res.setMsg("User with id: "+id+" is found");
			res.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(res, HttpStatus.FOUND);
		}
		else {
			return null; //no user found exception
		}
	}
}
