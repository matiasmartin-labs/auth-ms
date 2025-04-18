package com.mmartin.authms.domain.exception;

public class PasswordEmptyException extends ValidationException {

    private static final String MESSAGE = "Password can't be empty";

    public PasswordEmptyException() {
        super(MESSAGE);
    }
}
