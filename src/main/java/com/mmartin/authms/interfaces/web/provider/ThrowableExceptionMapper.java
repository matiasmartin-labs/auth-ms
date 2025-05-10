package com.mmartin.authms.interfaces.web.provider;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
class ThrowableExceptionMapper extends HandlerExceptionMapper<Throwable> {

    ThrowableExceptionMapper() {
        super(Response.Status.INTERNAL_SERVER_ERROR);
    }
}
