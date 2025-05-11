package com.mmartin.authms.interfaces.web.mapper;

import com.mmartin.authms.application.command.SignInCommand;
import com.mmartin.authms.application.command.SignUpCommand;
import com.mmartin.authms.interfaces.web.dto.SignInRequest;
import com.mmartin.authms.interfaces.web.dto.SignUpRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ApiMapperTest {

    @InjectMocks
    private ApiMapper apiMapper;

    @Test
    void given_SignUpRequest_when_call_map_then_return_SignUpCommand() {
        final var signUpRequest = new SignUpRequest("username", "password");

        final var actual = apiMapper.map(signUpRequest);

        final var expected = new SignUpCommand("username", "password");

        assertThat(actual)
                .isEqualTo(expected);
    }

    @Test
    void given_SignUpRequest_null_when_call_map_then_return_null() {

        final var actual = apiMapper.map((SignUpRequest) null);

        assertThat(actual)
                .isNull();
    }

    @Test
    void given_SignInRequest_when_call_map_then_return_SignInCommand() {
        final var signInRequest = new SignInRequest("username", "password");

        final var actual = apiMapper.map(signInRequest);

        final var expected = new SignInCommand("username", "password");

        assertThat(actual)
                .isEqualTo(expected);
    }

    @Test
    void given_SignInRequest_null_when_call_map_then_return_null() {

        final var actual = apiMapper.map((SignInRequest) null);

        assertThat(actual)
                .isNull();
    }
}
