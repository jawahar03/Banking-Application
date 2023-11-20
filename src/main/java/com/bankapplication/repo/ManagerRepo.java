package com.bankapplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankapplication.dto.Manager;

public interface ManagerRepo extends JpaRepository<Manager, Integer>{

}
