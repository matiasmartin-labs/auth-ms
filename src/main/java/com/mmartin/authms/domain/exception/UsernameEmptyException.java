package com.mmartin.authms.domain.exception;

public class UsernameEmptyException extends ValidationException {

    private static final String MESSAGE = "Username can't be empty";

    public UsernameEmptyException() {
        super(MESSAGE);
    }
}
