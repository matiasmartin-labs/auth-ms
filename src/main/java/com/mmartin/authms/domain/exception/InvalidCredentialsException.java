package com.mmartin.authms.domain.exception;

public class InvalidCredentialsException extends DomainException {

    private static final String MESSAGE = "Invalid credentials";

    public InvalidCredentialsException() {
        super(MESSAGE);
    }

}
