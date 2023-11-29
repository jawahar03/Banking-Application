package com.bankapplication.exception;

import lombok.Getter;

@Getter
public class AddressNotFound extends RuntimeException
{
	String msg;

	public AddressNotFound(String msg) {
		super();
		this.msg = msg;
	}
	
}
