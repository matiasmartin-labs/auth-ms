package com.mmartin.authms.application.usecase;

import com.mmartin.authms.domain.model.Authorization;
import com.mmartin.authms.domain.provider.TokenProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ValidateTokenUseCaseTest {

    @Mock
    private TokenProvider tokenProvider;

    @InjectMocks
    private ValidateTokenUseCaseImpl validateTokenUseCase;

    @Test
    void given_authorization_when_call_validate_then_success() {
        final var authorization = mock(Authorization.class);

        validateTokenUseCase.validate(authorization);

        verify(tokenProvider).validate(authorization);
    }

    @Test
    void given_authorization_null_when_call_validate_then_throw_exception() {

        assertThatThrownBy(() -> validateTokenUseCase.validate(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("authorization can't be null");

        verify(tokenProvider, never()).validate(any());
    }
}
