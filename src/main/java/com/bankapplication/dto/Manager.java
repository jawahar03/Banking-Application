package com.bankapplication.dto;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Getter
@Setter

public class Manager 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int managerId;
	@Min(value= 600000000l, message= "Invalid number")
	@Max(value = 999999999l, message="Invalid Number")
	private long contact;
	@OneToOne(cascade = CascadeType.ALL)
	private Branch branch;
	@NotNull
	@NotBlank
	private String name;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", message="Enter a strong password")
	private String password;
}

