package com.bankapplication.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bankapplication.dto.Address;
import com.bankapplication.dto.Bank;
import com.bankapplication.repo.AddressRepo;

@Repository
public class AddressDao 
{
	@Autowired
	AddressRepo repo;
	
	public Address saveAddress(Address a)
	{
		return repo.save(a);
	}
	
	public Address findAddress(int id)
	{
		Optional<Address> acc = repo.findById(id);
		return acc.get() ;
	}
	
	public Address deleteAddress(int id)
	{
		Address acc = findAddress(id);
		if(acc != null)
		{
			repo.delete(acc);
			return acc;
		}
		return null;
	}
	public List<Address> findAllAddress()
	{
		return repo.findAll();
	}
	public Address updateAddress(int id, Address b) {
		Address ex = findAddress(id);
		if(ex != null)
		{
			b.setAddressId(id);
			return repo.save(b);
		}
		return null;
	}
	
}
