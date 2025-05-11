package com.mmartin.authms.interfaces.web.provider;

import com.mmartin.authms.domain.exception.InvalidCredentialsException;
import com.mmartin.authms.interfaces.web.dto.ErrorResponse;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InvalidCredentialsExceptionMapperTest {

    private final InvalidCredentialsExceptionMapper mapper = new InvalidCredentialsExceptionMapper();

    @Test
    void given_InvalidCredentialsException_when_toResponse_then_response_entity() {
        final var exception = new InvalidCredentialsException();

        final var actual = mapper.toResponse(exception);

        assertThat(actual).isNotNull();
        assertThat(actual.getStatus()).isEqualTo(Response.Status.UNAUTHORIZED.getStatusCode());
        assertThat(actual.getEntity()).isEqualTo(ErrorResponse.builder()
                .code(Response.Status.UNAUTHORIZED.getReasonPhrase())
                .status(Response.Status.UNAUTHORIZED.getStatusCode())
                .message(exception.getMessage())
                .build());
    }
}
