package com.mmartin.authms.application.command;

import com.mmartin.authms.domain.model.Authorization;
import com.mmartin.authms.domain.usecase.ValidateTokenUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ValidateTokenCommandHandlerTest {

    @Mock
    private ValidateTokenUseCase validateTokenUseCase;

    @InjectMocks
    private ValidateTokenCommandHandler validateTokenCommandHandler;

    @Test
    void given_command_when_call_execute_then_success_executed() {
        final var command = new ValidateTokenCommand("super-token");

        final var actual = this.validateTokenCommandHandler.execute(command);

        assertThat(actual)
                .isNull();

        verify(this.validateTokenUseCase).validate(new Authorization("super-token"));
    }
}
