package com.bankapplication.dto;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Getter
@Setter
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private int name;
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	@OneToOne(cascade = CascadeType.ALL)	
	private Account account;
	@OneToOne
	private Branch branch;
}
