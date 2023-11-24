package com.bankapplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bankapplication.dto.Manager;

public interface ManagerRepo extends JpaRepository<Manager, Integer>
{
	@Query("select m from Manager m where m.name = ?1")
	public Manager findManager(String name);
}
