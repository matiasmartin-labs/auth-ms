package com.mmartin.authms.application.command;

import com.mmartin.cqrs.command.Command;
import com.mmartin.cqrs.command.CommandRequest;
import lombok.Builder;

@Builder
@Command("validate-token-command")
public record ValidateTokenCommand(
        String token
) implements CommandRequest<Void> { }
