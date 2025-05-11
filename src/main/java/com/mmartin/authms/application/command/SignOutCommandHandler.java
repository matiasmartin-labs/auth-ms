package com.mmartin.authms.application.command;

import com.mmartin.authms.domain.model.Authorization;
import com.mmartin.authms.domain.usecase.LogoutUseCase;
import com.mmartin.cqrs.command.CommandHandler;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
class SignOutCommandHandler implements CommandHandler<SignOutCommand, Void> {

    private final LogoutUseCase logoutUseCase;

    @Override
    public Void execute(SignOutCommand command) {
        final Authorization authorization = new Authorization(command.token());
        logoutUseCase.logout(authorization);
        return null;
    }
}
