package com.mmartin.authms.infrastructure.presentation.provider;

import com.mmartin.authms.infrastructure.presentation.dto.ErrorResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
class ThrowableExceptionMapper implements ExceptionMapper<Throwable> {
    @Override
    public Response toResponse(Throwable throwable) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(ErrorResponse.builder()
                        .code(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase())
                        .status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode())
                        .message(throwable.getMessage())
                        .build())
                .build();
    }
}
