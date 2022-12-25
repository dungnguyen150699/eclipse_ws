package com.example.demo.error;



public class ForbiddenException extends RuntimeException{
    private static final long serialVersionUID = 0L;

    public ForbiddenException(String err) {
        super(err);
    }
}