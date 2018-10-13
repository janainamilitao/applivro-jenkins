package com.domain.applivro.exceptions;

public class EmailUniqueException extends Exception{

	private static final long serialVersionUID = 1L;
	
    public EmailUniqueException() throws Exception {
		throw new Exception("Email cadastratado.");
	}

}
