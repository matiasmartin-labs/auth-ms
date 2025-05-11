package com.mmartin.authms.interfaces.web.provider;

import com.mmartin.authms.domain.exception.PasswordEmptyException;
import com.mmartin.authms.interfaces.web.dto.ErrorResponse;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ValidationExceptionMapperTest {

    private final ValidationExceptionMapper mapper = new ValidationExceptionMapper();

    @Test
    void given_ValidationExceptionMapper_when_toResponse_then_response_entity() {
        final var exception = new PasswordEmptyException();

        final var actual = mapper.toResponse(exception);

        assertThat(actual).isNotNull();
        assertThat(actual.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
        assertThat(actual.getEntity()).isEqualTo(ErrorResponse.builder()
                .code(Response.Status.BAD_REQUEST.getReasonPhrase())
                .status(Response.Status.BAD_REQUEST.getStatusCode())
                .message(exception.getMessage())
                .build());
    }
}
