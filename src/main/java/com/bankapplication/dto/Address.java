package com.bankapplication.dto;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Getter
@Setter
public class Address 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressId;
	@Min(value= 600000000l, message= "Invalid number")
	@Max(value = 999999999l, message="Invalid Number")
	private long contact;
	private String state;
	private String city;
	@Min(value= 110001, message="Invalid pincode")
	@Max(value=999999, message="Invalid pincode")
	private int pincode;
	
}
