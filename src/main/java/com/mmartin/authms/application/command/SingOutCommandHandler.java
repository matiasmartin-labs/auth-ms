package com.mmartin.authms.application.command;

import com.mmartin.authms.domain.model.Authorization;
import com.mmartin.authms.domain.usecase.LogoutUseCase;
import com.mmartin.cqrs.command.CommandHandler;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
class SingOutCommandHandler implements CommandHandler<SingOutCommand, Void> {

    private final LogoutUseCase logoutUseCase;

    @Override
    public Void execute(SingOutCommand command) {
        final Authorization authorization = new Authorization(command.token());
        logoutUseCase.logout(authorization);
        return null;
    }
}
