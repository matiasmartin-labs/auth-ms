package com.mmartin.authms.infrastructure.presentation.controller;

import com.mmartin.authms.application.command.SingInCommand;
import com.mmartin.authms.cqrs.command.CommandBus;
import com.mmartin.authms.application.command.SingUpCommand;
import com.mmartin.authms.domain.model.Authorization;
import com.mmartin.authms.infrastructure.presentation.dto.SignInRequest;
import com.mmartin.authms.infrastructure.presentation.dto.SignInResponse;
import com.mmartin.authms.infrastructure.presentation.dto.SignUpRequest;
import com.mmartin.authms.infrastructure.presentation.mapper.ApiMapper;
import io.smallrye.common.annotation.Blocking;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

@Blocking
@Path("/v1/users")
@RequiredArgsConstructor
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class UserController {

    private final CommandBus commandBus;

    private final ApiMapper apiMapper;

    @POST
    @Path("/sign-up")
    public Void register(SignUpRequest request) {
        final SingUpCommand command = apiMapper.map(request);
        return commandBus.send(command);
    }

    @POST
    @Path("/sign-in")
    public Response login(SignInRequest request) {
        final SingInCommand command = apiMapper.map(request);
        final Authorization authorization = commandBus.send(command);
        return Response.ok(new SignInResponse(command.username(), authorization.token()))
                .build();
    }
}
