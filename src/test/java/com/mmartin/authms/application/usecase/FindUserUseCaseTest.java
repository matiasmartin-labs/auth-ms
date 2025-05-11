package com.mmartin.authms.application.usecase;

import com.mmartin.authms.domain.model.User;
import com.mmartin.authms.domain.model.vo.Username;
import com.mmartin.authms.domain.repository.FindUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindUserUseCaseTest {

    @Mock
    private FindUserRepository findUserRepository;

    @InjectMocks
    private FindUserUseCaseImpl findUserUseCase;

    @Test
    void given_username_and_exists_when_call_findUser_then_return_optional_user() {
        final var username = mock(Username.class);
        final var user = mock(User.class);

        when(findUserRepository.findUser(username)).thenReturn(Optional.of(user));

        final var actual = findUserUseCase.findUser(username);

        assertThat(actual)
                .contains(user);

        verify(findUserRepository).findUser(username);
    }

    @Test
    void given_username_and_not_exists_when_call_findUser_then_optional_empty() {
        final var username = mock(Username.class);

        when(findUserRepository.findUser(username)).thenReturn(Optional.empty());

        final var actual = findUserUseCase.findUser(username);

        assertThat(actual)
                .isEmpty();

        verify(findUserRepository).findUser(username);
    }

    @Test
    void given_username_null_when_call_findUser_then_throw_exception() {

        assertThatThrownBy(() -> findUserUseCase.findUser(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("username can't be null");

        verify(findUserRepository, never()).findUser(any());
    }
}
