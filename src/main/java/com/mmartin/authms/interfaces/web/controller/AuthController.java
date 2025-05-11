package com.mmartin.authms.interfaces.web.controller;

import com.mmartin.authms.application.command.SignInCommand;
import com.mmartin.authms.application.command.SignOutCommand;
import com.mmartin.authms.application.command.ValidateTokenCommand;
import com.mmartin.authms.application.command.SignUpCommand;
import com.mmartin.authms.domain.model.Authorization;
import com.mmartin.authms.interfaces.web.dto.SignInRequest;
import com.mmartin.authms.interfaces.web.dto.SignInResponse;
import com.mmartin.authms.interfaces.web.dto.SignUpRequest;
import com.mmartin.authms.interfaces.web.mapper.ApiMapper;
import com.mmartin.cqrs.command.CommandBus;
import io.quarkus.security.Authenticated;
import io.smallrye.common.annotation.Blocking;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

@Blocking
@Path("/v1/auth")
@RequiredArgsConstructor
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class AuthController {

    private final CommandBus commandBus;

    private final ApiMapper apiMapper;

    @POST
    @Path("/sign-up")
    public Response register(SignUpRequest request) {
        final SignUpCommand command = apiMapper.map(request);
        commandBus.send(command);
        return Response.status(Response.Status.CREATED)
                .build();
    }

    @POST
    @Path("/sign-in")
    public Response login(SignInRequest request) {
        final SignInCommand command = apiMapper.map(request);
        final Authorization authorization = commandBus.send(command);
        return Response.ok(new SignInResponse(command.username(), authorization.token()))
                .build();
    }

    @POST
    @Path("/sign-out")
    @Authenticated
    public Response logout(@HeaderParam("Authorization") String authorization) {
        final SignOutCommand command = new SignOutCommand(authorization);
        commandBus.send(command);
        return Response.noContent().build();
    }

    @POST
    @Path("/token/validate")
    @Authenticated
    public Response validate(@HeaderParam("Authorization") String authorization) {
        final ValidateTokenCommand command = new ValidateTokenCommand(authorization);
        commandBus.send(command);
        return Response.noContent().build();
    }
}
