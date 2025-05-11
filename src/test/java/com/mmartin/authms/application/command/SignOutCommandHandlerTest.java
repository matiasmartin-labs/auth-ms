package com.mmartin.authms.application.command;

import com.mmartin.authms.domain.model.Authorization;
import com.mmartin.authms.domain.usecase.LogoutUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SignOutCommandHandlerTest {

    @Mock
    private LogoutUseCase logoutUseCase;

    @InjectMocks
    private SignOutCommandHandler signOutCommandHandler;

    @Test
    void given_command_when_call_execute_then_success_executed() {
        final var command = new SignOutCommand("super-token");

        final var actual = this.signOutCommandHandler.execute(command);

        assertThat(actual)
                .isNull();

        verify(this.logoutUseCase).logout(new Authorization("super-token"));
    }
}
