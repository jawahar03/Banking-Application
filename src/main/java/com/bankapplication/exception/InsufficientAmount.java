package com.bankapplication.exception;

import lombok.Getter;

@Getter
public class InsufficientAmount extends RuntimeException
{
	String msg;

	public InsufficientAmount(String msg) {
		super();
		this.msg = msg;
	}
	
}
