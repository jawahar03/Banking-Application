package com.bankapplication.exception;

import lombok.Getter;

@Getter
public class IncorrectPassword extends RuntimeException
{
	String msg;

	public IncorrectPassword(String msg) {
		super();
		this.msg = msg;
	}
	
	
}
