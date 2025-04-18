package com.mmartin.authms.application.command;

import com.mmartin.authms.cqrs.command.CommandHandler;
import com.mmartin.authms.domain.exception.AlreadyExistsUsernameException;
import com.mmartin.authms.domain.model.User;
import com.mmartin.authms.domain.model.vo.Username;
import com.mmartin.authms.domain.usecase.ExistsUserUseCase;
import com.mmartin.authms.domain.usecase.SaveUserUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
class SingUpCommandHandler implements CommandHandler<SingUpCommand, Void> {

    private final ExistsUserUseCase existsUserUseCase;
    private final SaveUserUseCase saveUserUseCase;

    @Override
    public Void execute(SingUpCommand command) {
        final boolean exists = existsUserUseCase.exists(new Username(command.username()));
        if (exists) {
            throw new AlreadyExistsUsernameException();
        }
        final User user = User.signUp(command);
        saveUserUseCase.save(user);
        return null;
    }
}
