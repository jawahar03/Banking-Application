package com.bankapplication.exception;

import lombok.Getter;

@Getter
public class BankNotFound extends RuntimeException
{
	String msg;

	public BankNotFound(String msg) {
		super();
		this.msg = msg;
	}
	
}
