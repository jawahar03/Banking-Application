package com.bankapplication.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bankapplication.dto.User;
import com.bankapplication.repo.UserRepo;

@Repository
public class UserDao 
{
	@Autowired
	UserRepo repo;
	
	public User saveUser(User a)
	{
		return repo.save(a);
	}
	
	public User findUser(int id)
	{
		Optional<User> acc = repo.findById(id);
		return acc.get() ;
	}
	
	public User deleteUser(int id)
	{
		User acc = findUser(id);
		if(acc != null)
		{
			repo.delete(acc);
			return acc;
		}
		return null;
	}
	public List<User> findAllUser()
	{
		return repo.findAll();
	}
	public User updateUser(int id, User b) {
		User ex = findUser(id);
		if(ex != null)
		{
			b.setUserId(id);
			return repo.save(b);
		}
		return null;
	}
}
