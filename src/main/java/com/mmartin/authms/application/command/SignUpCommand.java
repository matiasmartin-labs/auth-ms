package com.mmartin.authms.application.command;

import com.mmartin.cqrs.command.Command;
import com.mmartin.cqrs.command.CommandRequest;
import lombok.Builder;

@Builder
@Command("sign-up-command")
public record SignUpCommand(
        String username,
        String password
) implements CommandRequest<Void> { }
