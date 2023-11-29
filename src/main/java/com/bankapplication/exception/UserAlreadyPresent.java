package com.bankapplication.exception;

import lombok.Getter;

@Getter
public class UserAlreadyPresent extends RuntimeException 
{
	String msg;

	public UserAlreadyPresent(String msg) {
		super();
		this.msg = msg;
	}
	
}
