package com.mmartin.authms.domain.exception;

@SuppressWarnings("java:S110")
public class UsernameEmptyException extends ValidationException {

    private static final String MESSAGE = "Username can't be empty";

    public UsernameEmptyException() {
        super(MESSAGE);
    }
}
