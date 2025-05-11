package com.mmartin.authms.domain.exception;

@SuppressWarnings("java:S110")
public class AlreadyExistsUsernameException extends ValidationException {

    private static final String MESSAGE = "Already exists";

    public AlreadyExistsUsernameException() {
        super(MESSAGE);
    }
}
