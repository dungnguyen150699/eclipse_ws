package com.example.demo.common.error;



public class BadRequestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4241128370852677454L;

	public BadRequestException(String err) {
		super(err);
	}

}
