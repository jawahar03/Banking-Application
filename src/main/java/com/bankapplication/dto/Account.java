package com.bankapplication.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Getter
@Setter
public class Account 
{
	private int accountId;
	private int accountNumber;
	private double balance;
	private String password;
	@OneToOne(cascade = CascadeType.ALL)
	private User user;
	private AccountType type;
	@OneToOne(cascade = CascadeType.ALL)
	private List<Transaction> transaction;
}
