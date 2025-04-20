package com.mmartin.authms.infrastructure.presentation.provider;

import com.mmartin.authms.domain.exception.InvalidCredentialsException;
import com.mmartin.authms.infrastructure.presentation.dto.ErrorResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
class InvalidCredentialsExceptionMapper extends HandlerExceptionMapper<InvalidCredentialsException> {

    InvalidCredentialsExceptionMapper() {
        super(Response.Status.UNAUTHORIZED);
    }
}
