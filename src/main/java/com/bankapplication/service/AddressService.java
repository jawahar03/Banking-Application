package com.bankapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bankapplication.config.ResponseStructure;
import com.bankapplication.dao.AddressDao;
import com.bankapplication.dao.BranchDao;
import com.bankapplication.dao.UserDao;
import com.bankapplication.dto.Address;
import com.bankapplication.dto.Branch;
import com.bankapplication.dto.User;

@Service
public class AddressService 
{
	@Autowired
	AddressDao adao;
	@Autowired
	UserDao udao;
	@Autowired
	BranchDao bdao;
	
	public ResponseEntity<ResponseStructure<User>> createAddressForUser(int uId, Address address)
	{
		ResponseStructure<User> res = new ResponseStructure<>();
		User user = udao.findUser(uId);
		adao.saveAddress(address);
		user.setAddress(address);
		User updatedUser = udao.updateUser(uId, user);
		res.setData(updatedUser);
		res.setMsg("Address added for user id: " +uId);
		return new ResponseEntity<ResponseStructure<User>>(res, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Branch>> createAddressForBranch(int bId, Address address)
	{
		ResponseStructure<Branch> res = new ResponseStructure<>();
		Branch branch = bdao.findBranch(bId);
		adao.saveAddress(address);
		branch.setAddress(address);
		Branch savedBranch = bdao.updateBranch(bId, branch);
		res.setData(savedBranch);
		res.setMsg("Address added for user id: " +bId);
		return new ResponseEntity<ResponseStructure<Branch>>(res, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Address>> updateAddress(int id , Address a)
	{
		ResponseStructure<Address> res = new ResponseStructure<>();
		Address address = adao.findAddress(id);
		if(address!=null)
		{
			a.setAddressId(address.getAddressId());
			res.setData(adao.updateAddress(id, address));
			res.setMsg("Address has neen updated");
			res.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Address>>(res, HttpStatus.CREATED);
		}
		else {
			return null; //no address found
		}
		
		
	}
}
