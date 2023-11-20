package com.bankapplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankapplication.dto.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
