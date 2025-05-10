package com.mmartin.authms.interfaces.web.provider;

import com.mmartin.authms.domain.exception.ValidationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
class ValidationExceptionMapper extends HandlerExceptionMapper<ValidationException> {

    ValidationExceptionMapper() {
        super(Response.Status.BAD_REQUEST);
    }
}
