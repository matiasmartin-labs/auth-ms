package com.mmartin.authms.domain.exception;

public class AlreadyExistsUsernameException extends ValidationException {

    private static final String MESSAGE = "Already exists";

    public AlreadyExistsUsernameException() {
        super(MESSAGE);
    }
}
