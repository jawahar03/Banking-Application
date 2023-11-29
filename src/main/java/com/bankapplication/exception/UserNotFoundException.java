package com.bankapplication.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException 
{
	String msg;

	public UserNotFoundException(String msg) {
		super();
		this.msg = msg;
	}
	
	
}
     