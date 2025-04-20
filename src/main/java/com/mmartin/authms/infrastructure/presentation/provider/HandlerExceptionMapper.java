package com.mmartin.authms.infrastructure.presentation.provider;

import com.mmartin.authms.infrastructure.presentation.dto.ErrorResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
abstract class HandlerExceptionMapper<T extends Throwable> implements ExceptionMapper<T> {

    private final Response.Status status;

    @Override
    public final Response toResponse(T throwable) {
        return Response.status(this.status)
                .entity(ErrorResponse.builder()
                        .code(this.status.getReasonPhrase())
                        .status(this.status.getStatusCode())
                        .message(throwable.getMessage())
                        .build())
                .build();
    }
}
