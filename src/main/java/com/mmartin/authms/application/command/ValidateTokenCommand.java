package com.mmartin.authms.application.command;

import com.mmartin.authms.cqrs.command.Command;
import com.mmartin.authms.cqrs.command.CommandRequest;
import lombok.Builder;

@Builder
@Command("validate-token-command")
public record ValidateTokenCommand(
        String token
) implements CommandRequest<Void>  { }
