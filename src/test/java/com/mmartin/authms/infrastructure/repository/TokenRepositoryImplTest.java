package com.mmartin.authms.infrastructure.repository;

import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.value.ValueCommands;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TokenRepositoryImplTest {

    @Mock
    private RedisDataSource redisDataSource;

    @Mock
    private ValueCommands<String, String> valueCommands;

    private TokenRepositoryImpl tokenRepository;

    @BeforeEach
    void setup() {
        when(this.redisDataSource.value(String.class, String.class)).thenReturn(valueCommands);
        this.tokenRepository = new TokenRepositoryImpl(redisDataSource);
    }

    @Test
    void given_jti_and_has_value_when_call_isRevoked_then_return_true() {
        when(this.valueCommands.get("jti-id")).thenReturn("some-value");

        final var actual = this.tokenRepository.isRevoked("jti-id");

        assertThat(actual)
                .isTrue();

        verify(this.valueCommands).get("jti-id");
    }

    @ParameterizedTest
    @NullAndEmptySource
    void given_jti_and_empty_value_when_call_isRevoked_then_return_false(String value) {
        when(this.valueCommands.get("jti-id")).thenReturn(value);

        final var actual = this.tokenRepository.isRevoked("jti-id");

        assertThat(actual)
                .isFalse();

        verify(this.valueCommands).get("jti-id");
    }

    @Test
    void given_jti_when_call_revoke_then_persist_revoked() {
        this.tokenRepository.revoke("jti-id", 1000L);

        verify(this.valueCommands).setex(eq("jti-id"), anyLong(), eq("revoked"));
    }
}
