package com.bankapplication.exception;

import lombok.Getter;

@Getter
public class InvalidAccountType extends RuntimeException
{
	String msg;

	public InvalidAccountType(String msg) {
		super();
		this.msg = msg;
	}
}
