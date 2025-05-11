package com.mmartin.authms.application.usecase;

import com.mmartin.authms.domain.model.vo.Username;
import com.mmartin.authms.domain.repository.ExistsUserRepository;
import com.mmartin.authms.domain.usecase.ExistsUserUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExistsUserUseCaseTest {

    @Mock
    private ExistsUserRepository existsUserRepository;

    @InjectMocks
    private ExistsUserUseCaseImpl existsUserUseCase;

    @Test
    void given_username_and_exists_when_call_exists_then_return_true() {
        final var username = mock(Username.class);

        when(existsUserRepository.exists(username)).thenReturn(true);

        final var actual = existsUserUseCase.exists(username);

        assertThat(actual)
                .isTrue();

        verify(existsUserRepository).exists(username);
    }

    @Test
    void given_username_and_not_exists_when_call_exists_then_return_false() {
        final var username = mock(Username.class);

        when(existsUserRepository.exists(username)).thenReturn(false);

        final var actual = existsUserUseCase.exists(username);

        assertThat(actual)
                .isFalse();

        verify(existsUserRepository).exists(username);
    }

    @Test
    void given_username_null_when_call_exists_then_throw_exception() {

        assertThatThrownBy(() -> existsUserUseCase.exists(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("username can't be null");

        verify(existsUserRepository, never()).exists(any());
    }
}
