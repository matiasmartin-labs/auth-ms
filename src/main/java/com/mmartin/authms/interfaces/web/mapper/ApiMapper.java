package com.mmartin.authms.interfaces.web.mapper;

import com.mmartin.authms.application.command.SingInCommand;
import com.mmartin.authms.application.command.SingUpCommand;
import com.mmartin.authms.interfaces.web.dto.SignInRequest;
import com.mmartin.authms.interfaces.web.dto.SignUpRequest;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ApiMapper {

    public SingUpCommand map(SignUpRequest src) {
        return SingUpCommand.builder()
                .password(src.password())
                .username(src.username())
                .build();
    }

    public SingInCommand map(SignInRequest src) {
        return SingInCommand.builder()
                .password(src.password())
                .username(src.username())
                .build();
    }
}
