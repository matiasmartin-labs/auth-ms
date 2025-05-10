package com.mmartin.authms.application.command;

import com.mmartin.authms.domain.model.Authorization;
import com.mmartin.authms.domain.usecase.ValidateTokenUseCase;
import com.mmartin.cqrs.command.CommandHandler;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
class ValidateTokenCommandHandler implements CommandHandler<ValidateTokenCommand, Void> {

    private final ValidateTokenUseCase validateTokenUseCase;

    @Override
    public Void execute(ValidateTokenCommand command) {
        final Authorization authorization = new Authorization(command.token());
        validateTokenUseCase.validate(authorization);
        return null;
    }
}
