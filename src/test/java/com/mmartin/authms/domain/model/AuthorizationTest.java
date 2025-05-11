package com.mmartin.authms.domain.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AuthorizationTest {

    @Test
    void given_token_then_creation_then_return_instance() {
        final var token = "token";

        final var actual = new Authorization(token);

        assertThat(actual).isNotNull();
        assertThat(actual.token()).isEqualTo(token);
    }

    @Test
    void given_brear_token_then_creation_then_return_instance() {
        final var token = "token";

        final var actual = new Authorization("Bearer %s".formatted(token));

        assertThat(actual).isNotNull();
        assertThat(actual.token()).isEqualTo(token);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void given_parameters_then_creation_then_throw_exception(String token) {

        assertThatThrownBy(() -> new Authorization(token))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
