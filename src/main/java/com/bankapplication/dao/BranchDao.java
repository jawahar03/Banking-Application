package com.bankapplication.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bankapplication.dto.Branch;
import com.bankapplication.repo.BranchRepo;

@Repository
public class BranchDao 
{
	@Autowired
	BranchRepo repo;
	
	public Branch saveBranch(Branch a)
	{
		return repo.save(a);
	}
	
	public Branch findBranch(int id)
	{
		Optional<Branch> acc = repo.findById(id);
		return acc.get() ;
	}
	
	public Branch deleteBranch(int id)
	{
		Branch acc = findBranch(id);
		if(acc != null)
		{
			repo.delete(acc);
			return acc;
		}
		return null;
	}
	public List<Branch> findAllBranch()
	{
		return repo.findAll();
	}
	public Branch updateBranch(int id, Branch b) {
		Branch ex = findBranch(id);
		if(ex != null)
		{
			b.setBranchId(id);
			return repo.save(b);
		}
		return null;
	}
}
