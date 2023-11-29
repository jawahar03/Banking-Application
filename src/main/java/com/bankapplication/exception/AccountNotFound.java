package com.bankapplication.exception;

import lombok.Getter;

@Getter
public class AccountNotFound extends RuntimeException
{
	String msg;

	public AccountNotFound(String msg) {
		super();
		this.msg = msg;
	}
	
}
