package com.bankapplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bankapplication.dto.User;

public interface UserRepo extends JpaRepository<User, Integer>
{
	@Query("select u from User u where u.name =?1")
	public User findByName(String name);
}
