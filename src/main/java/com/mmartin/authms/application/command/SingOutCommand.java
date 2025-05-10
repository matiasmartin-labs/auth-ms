package com.mmartin.authms.application.command;

import com.mmartin.cqrs.command.Command;
import com.mmartin.cqrs.command.CommandRequest;
import lombok.Builder;

@Builder
@Command("sing-out-command")
public record SingOutCommand(
        String token
) implements CommandRequest<Void> { }
