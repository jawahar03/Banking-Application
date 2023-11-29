package com.bankapplication.exception;

import lombok.Getter;

@Getter
public class InvalidAmount extends RuntimeException{

	String msg;

	public InvalidAmount(String msg) {
		super();
		this.msg = msg;
	}
	
}
