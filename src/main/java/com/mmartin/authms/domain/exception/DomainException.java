package com.mmartin.authms.domain.exception;

public class DomainException extends RuntimeException {

    DomainException(String message) {
        super(message);
    }

    DomainException(String message, Throwable e) {
        super(message, e);
    }
}
