package com.mmartin.authms.application.command;

import com.mmartin.authms.domain.exception.AlreadyExistsUsernameException;
import com.mmartin.authms.domain.model.User;
import com.mmartin.authms.domain.model.vo.Username;
import com.mmartin.authms.domain.usecase.ExistsUserUseCase;
import com.mmartin.authms.domain.usecase.SaveUserUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SignUpCommandHandlerTest {

    private MockedStatic<User> userMockedStatic;

    @Mock
    private ExistsUserUseCase existsUserUseCase;

    @Mock
    private SaveUserUseCase saveUserUseCase;

    @InjectMocks
    private SignUpCommandHandler signUpCommandHandler;

    @BeforeEach
    void setup() {
        this.userMockedStatic = mockStatic(User.class);
    }

    @AfterEach
    void tearDown() {
        this.userMockedStatic.close();
    }

    @Test
    void given_command_valid_when_call_execute_then_success_executed() {
        final var command = new SignUpCommand("username", "password");
        final var user = mock(User.class);

        when(this.existsUserUseCase.exists(new Username("username"))).thenReturn(false);
        this.userMockedStatic.when(() -> User.signUp(command)).thenReturn(user);

        signUpCommandHandler.execute(command);

        verify(this.existsUserUseCase).exists(new Username("username"));
        this.userMockedStatic.verify(() -> User.signUp(command));
        verify(this.saveUserUseCase).save(user);
    }

    @Test
    void given_command_invalid_when_call_execute_then_success_executed() {
        final var command = new SignUpCommand("username", "password");

        when(this.existsUserUseCase.exists(new Username("username"))).thenReturn(true);

        assertThatThrownBy(() -> signUpCommandHandler.execute(command))
                .isInstanceOf(AlreadyExistsUsernameException.class);

        verify(this.existsUserUseCase).exists(new Username("username"));
        this.userMockedStatic.verify(() -> User.signUp(any()), never());
        verify(this.saveUserUseCase, never()).save(any());
    }
}
