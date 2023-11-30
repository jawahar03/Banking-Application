package com.bankapplication.dto;


import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Getter
@Setter
public class Account 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accountId;
	private int accountNumber;
	private double balance;
	private String password;
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private User user;
	private AccountType type;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Transaction> transaction;
}

