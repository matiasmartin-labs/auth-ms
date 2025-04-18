package com.mmartin.authms.infrastructure.presentation.provider;

import com.mmartin.authms.domain.exception.InvalidCredentialsException;
import com.mmartin.authms.infrastructure.presentation.dto.ErrorResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
class InvalidCredentialsExceptionMapper implements ExceptionMapper<InvalidCredentialsException> {
    @Override
    public Response toResponse(InvalidCredentialsException exception) {
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(ErrorResponse.builder()
                        .code(Response.Status.UNAUTHORIZED.getReasonPhrase())
                        .status(Response.Status.UNAUTHORIZED.getStatusCode())
                        .message(exception.getMessage())
                        .build())
                .build();
    }
}
