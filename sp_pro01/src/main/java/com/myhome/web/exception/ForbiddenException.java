package com.myhome.web.exception;

public class ForbiddenException {
	
	public ForbiddenException() {}
	
	public ForbiddenException(String message) {
		super(message, new Throwable());
	}
	
}
