package com.mmartin.authms.interfaces.web.provider;

import com.mmartin.authms.interfaces.web.dto.ErrorResponse;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ThrowableExceptionMapperTest {

    private final ThrowableExceptionMapper mapper = new ThrowableExceptionMapper();

    @Test
    void given_Exception_when_toResponse_then_response_entity() {
        final var exception = new Exception();

        final var actual = mapper.toResponse(exception);

        assertThat(actual).isNotNull();
        assertThat(actual.getStatus()).isEqualTo(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        assertThat(actual.getEntity()).isEqualTo(ErrorResponse.builder()
                .code(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode())
                .message(exception.getMessage())
                .build());
    }
}
