package com.mmartin.authms.application.usecase;

import com.mmartin.authms.domain.model.Authorization;
import com.mmartin.authms.domain.model.User;
import com.mmartin.authms.domain.provider.TokenProvider;
import com.mmartin.authms.domain.repository.SaveUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LogoutUseCaseTest {

    @Mock
    private TokenProvider tokenProvider;

    @InjectMocks
    private LogoutUseCaseImpl logoutUseCase;

    @Test
    void given_authorization_when_call_logout_then_success() {
        final var authorization = mock(Authorization.class);

        logoutUseCase.logout(authorization);

        verify(tokenProvider).revoke(authorization);
    }

    @Test
    void given_authorization_null_when_call_logout_then_throw_exception() {

        assertThatThrownBy(() -> logoutUseCase.logout(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("authorization can't be null");

        verify(tokenProvider, never()).revoke(any());
    }
}
