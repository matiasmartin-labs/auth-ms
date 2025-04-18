package com.mmartin.authms.application.command;

import com.mmartin.authms.cqrs.command.Command;
import com.mmartin.authms.cqrs.command.CommandRequest;
import com.mmartin.authms.domain.model.Authorization;
import lombok.Builder;

@Builder
@Command("sing-in-command")
public record SingInCommand(
        String username,
        String password
) implements CommandRequest<Authorization> { }
