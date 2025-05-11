package com.mmartin.authms.domain.provider;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PasswordEncoderProviderTest {

    @Test
    void given_instance_and_not_configured_when_get_instance_then_throw_exception() {

        assertThatThrownBy(PasswordEncoderProvider::instance)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("PasswordEncoderProvider not initialized");
    }
}
