package com.mmartin.authms.infrastructure.presentation.provider;

import com.mmartin.authms.infrastructure.presentation.dto.ErrorResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
class ThrowableExceptionMapper extends HandlerExceptionMapper<Throwable> {

    ThrowableExceptionMapper() {
        super(Response.Status.INTERNAL_SERVER_ERROR);
    }
}
