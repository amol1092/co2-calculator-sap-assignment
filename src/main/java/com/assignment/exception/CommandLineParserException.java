package com.assignment.exception;

public class CommandLineParserException extends Exception {

	private static final long serialVersionUID = -7673638255535750604L;
	
	public CommandLineParserException(String message, Throwable t) {
		super(message, t);
	}

}
