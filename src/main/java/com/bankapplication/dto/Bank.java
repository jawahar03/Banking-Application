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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Getter
@Setter
public class Bank 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bankId;
	@NotNull
	@NotBlank
	private String bankName;
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Branch> branch;
}
