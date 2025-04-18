package com.mmartin.authms.application.command;

import com.mmartin.authms.cqrs.command.CommandHandler;
import com.mmartin.authms.domain.exception.InvalidCredentialsException;
import com.mmartin.authms.domain.model.Authorization;
import com.mmartin.authms.domain.model.User;
import com.mmartin.authms.domain.model.vo.Password;
import com.mmartin.authms.domain.model.vo.Username;
import com.mmartin.authms.domain.usecase.FindUserUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
class SingInCommandHandler implements CommandHandler<SingInCommand, Authorization> {

    private final FindUserUseCase findUserUseCase;

    @Override
    public Authorization execute(SingInCommand command) {
        final Optional<User> userOpt = findUserUseCase.findUser(new Username(command.username()));

        if (userOpt.isEmpty()) {
            throw new InvalidCredentialsException();
        }

        final User user = userOpt.get();

        return user.signIn(new Password(command.password()));
    }
}
