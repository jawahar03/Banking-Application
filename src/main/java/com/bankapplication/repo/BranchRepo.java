package com.bankapplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankapplication.dto.Branch;

public interface BranchRepo extends JpaRepository<Branch, Integer>{

}
