package com.mmartin.authms.interfaces.web.mapper;

import com.mmartin.authms.application.command.SignInCommand;
import com.mmartin.authms.application.command.SignUpCommand;
import com.mmartin.authms.interfaces.web.dto.SignInRequest;
import com.mmartin.authms.interfaces.web.dto.SignUpRequest;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Objects;

@ApplicationScoped
public class ApiMapper {

    public SignUpCommand map(SignUpRequest src) {
        if (Objects.isNull(src)) {
            return null;
        }
        return SignUpCommand.builder()
                .password(src.password())
                .username(src.username())
                .build();
    }

    public SignInCommand map(SignInRequest src) {
        if (Objects.isNull(src)) {
            return null;
        }
        return SignInCommand.builder()
                .password(src.password())
                .username(src.username())
                .build();
    }
}
