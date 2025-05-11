package com.mmartin.authms.domain.exception;

@SuppressWarnings("java:S110")
public class PasswordEmptyException extends ValidationException {

    private static final String MESSAGE = "Password can't be empty";

    public PasswordEmptyException() {
        super(MESSAGE);
    }
}
