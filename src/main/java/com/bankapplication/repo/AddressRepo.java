package com.bankapplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankapplication.dto.Address;

public interface AddressRepo extends JpaRepository<Address, Integer>{

}
