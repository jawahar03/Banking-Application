package com.bankapplication.exception;

import lombok.Getter;

@Getter
public class InvalidAccountNumber extends RuntimeException
{
	String msg;

	public InvalidAccountNumber(String msg) {
		super();
		this.msg = msg;
	}
	
}
