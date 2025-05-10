package com.mmartin.authms.interfaces.web.provider;

import com.mmartin.authms.domain.exception.InvalidCredentialsException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
class InvalidCredentialsExceptionMapper extends HandlerExceptionMapper<InvalidCredentialsException> {

    InvalidCredentialsExceptionMapper() {
        super(Response.Status.UNAUTHORIZED);
    }
}
