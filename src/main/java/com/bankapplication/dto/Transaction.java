package com.bankapplication.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Getter
@Setter
public class Transaction 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionId;
	private double amount;
	private Account toAccount;
	private Date date;
	private TransactionType type;
	private TransactionStatus status;
	

}