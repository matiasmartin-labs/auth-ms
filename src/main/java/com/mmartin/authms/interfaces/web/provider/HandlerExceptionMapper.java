package com.mmartin.authms.interfaces.web.provider;

import com.mmartin.authms.interfaces.web.dto.ErrorResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
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
