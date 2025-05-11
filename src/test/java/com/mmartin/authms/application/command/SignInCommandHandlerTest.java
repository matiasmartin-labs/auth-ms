package com.mmartin.authms.application.command;

import com.mmartin.authms.domain.exception.InvalidCredentialsException;
import com.mmartin.authms.domain.model.Authorization;
import com.mmartin.authms.domain.model.User;
import com.mmartin.authms.domain.model.vo.Password;
import com.mmartin.authms.domain.model.vo.Username;
import com.mmartin.authms.domain.usecase.FindUserUseCase;
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
class SignInCommandHandlerTest {

    @Mock
    private FindUserUseCase findUserUseCase;

    @InjectMocks
    private SignInCommandHandler signInCommandHandler;

    @Test
    void given_command_valid_user_when_call_execute_then_return_authorization() {
        final var command = new SignInCommand("username", "password");
        final var expected = new Authorization("super-token");
        final var user = mock(User.class);

        when(this.findUserUseCase.findUser(new Username("username"))).thenReturn(Optional.of(user));
        when(user.signIn(new Password("password"))).thenReturn(expected);

        final var actual = signInCommandHandler.execute(command);

        assertThat(actual)
                .isEqualTo(expected);

        verify(this.findUserUseCase).findUser(new Username("username"));
        verify(user).signIn(new Password("password"));
    }

    @Test
    void given_command_invalid_user_when_call_execute_then_return_authorization() {
        final var command = new SignInCommand("username", "password");
        final var user = mock(User.class);

        when(this.findUserUseCase.findUser(new Username("username"))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> signInCommandHandler.execute(command))
                .isInstanceOf(InvalidCredentialsException.class);

        verify(this.findUserUseCase).findUser(new Username("username"));
        verify(user, never()).signIn(any());
    }
}
