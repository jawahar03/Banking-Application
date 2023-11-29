package com.bankapplication.exception;

import lombok.Getter;

@Getter
public class ManagerNotFound extends RuntimeException
{
	String msg;

	public ManagerNotFound(String msg) {
		super();
		this.msg = msg;
	}
	
}
