package com.mmartin.authms.infrastructure.presentation.provider;

import com.mmartin.authms.domain.exception.ValidationException;
import com.mmartin.authms.infrastructure.presentation.dto.ErrorResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

    @Override
    public Response toResponse(ValidationException exception) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(ErrorResponse.builder()
                        .code(Response.Status.BAD_REQUEST.getReasonPhrase())
                        .status(Response.Status.BAD_REQUEST.getStatusCode())
                        .message(exception.getMessage())
                        .build())
                .build();
    }
}
