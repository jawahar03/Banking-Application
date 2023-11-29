package com.bankapplication.exception;

import lombok.Getter;

@Getter
public class BranchNotFound extends RuntimeException{
	String msg;

	public BranchNotFound(String msg) {
		super();
		this.msg = msg;
	}
	
	
}
