package com.example.demo.common.error;



public class NotFoundException extends RuntimeException{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Constructs an <code>NotFoundException</code> with the
     * specified detail message.
     *
     * @param   s   the detail message.
     */
    public NotFoundException(String s) {
        super(s);
    }
}