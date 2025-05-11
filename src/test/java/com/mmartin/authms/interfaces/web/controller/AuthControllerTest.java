package com.mmartin.authms.interfaces.web.controller;

import com.mmartin.authms.application.command.SignInCommand;
import com.mmartin.authms.application.command.SignOutCommand;
import com.mmartin.authms.application.command.SignUpCommand;
import com.mmartin.authms.application.command.ValidateTokenCommand;
import com.mmartin.authms.domain.model.Authorization;
import com.mmartin.authms.interfaces.web.dto.SignInRequest;
import com.mmartin.authms.interfaces.web.dto.SignInResponse;
import com.mmartin.authms.interfaces.web.dto.SignUpRequest;
import com.mmartin.authms.interfaces.web.mapper.ApiMapper;
import com.mmartin.cqrs.command.CommandBus;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private CommandBus commandBus;

    @Mock
    private ApiMapper apiMapper;

    @InjectMocks
    private AuthController authController;

    @Test
    void given_request_when_login_then_return_authorization() {
        final var request = mock(SignInRequest.class);
        final var command = mock(SignInCommand.class);
        final var authorization = mock(Authorization.class);

        when(authorization.token()).thenReturn("token");
        when(command.username()).thenReturn("username");
        when(this.apiMapper.map(request)).thenReturn(command);
        when(this.commandBus.send(command)).thenReturn(authorization);

        final var actual = this.authController.login(request);

        assertThat(actual).isNotNull();
        assertThat(actual.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
        assertThat(actual.getEntity()).isEqualTo(new SignInResponse("username", "token"));

        verify(authorization).token();
        verify(command).username();
        verify(this.apiMapper).map(request);
        verify(this.commandBus).send(command);
    }

    @Test
    void given_request_when_register_then_return_created() {
        final var request = mock(SignUpRequest.class);
        final var command = mock(SignUpCommand.class);

        when(this.apiMapper.map(request)).thenReturn(command);

        final var actual = this.authController.register(request);

        assertThat(actual).isNotNull();
        assertThat(actual.getStatus()).isEqualTo(Response.Status.CREATED.getStatusCode());

        verify(this.apiMapper).map(request);
        verify(this.commandBus).send(command);
    }

    @Test
    void given_request_when_logout_then_return_no_content() {
        final var command = new SignOutCommand("super-token");

        final var actual = this.authController.logout("super-token");

        assertThat(actual).isNotNull();
        assertThat(actual.getStatus()).isEqualTo(Response.Status.NO_CONTENT.getStatusCode());

        verify(this.commandBus).send(command);
    }

    @Test
    void given_request_when_validate_then_return_no_content() {
        final var command = new ValidateTokenCommand("super-token");

        final var actual = this.authController.validate("super-token");

        assertThat(actual).isNotNull();
        assertThat(actual.getStatus()).isEqualTo(Response.Status.NO_CONTENT.getStatusCode());

        verify(this.commandBus).send(command);
    }
}
