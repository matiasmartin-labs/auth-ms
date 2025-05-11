package com.mmartin.authms.application.command;

import com.mmartin.authms.domain.exception.AlreadyExistsUsernameException;
import com.mmartin.authms.domain.model.User;
import com.mmartin.authms.domain.model.vo.Username;
import com.mmartin.authms.domain.usecase.ExistsUserUseCase;
import com.mmartin.authms.domain.usecase.SaveUserUseCase;
import com.mmartin.cqrs.command.CommandHandler;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
class SignUpCommandHandler implements CommandHandler<SignUpCommand, Void> {

    private final ExistsUserUseCase existsUserUseCase;
    private final SaveUserUseCase saveUserUseCase;

    @Override
    public Void execute(SignUpCommand command) {
        final boolean exists = existsUserUseCase.exists(new Username(command.username()));
        if (exists) {
            throw new AlreadyExistsUsernameException();
        }
        final User user = User.signUp(command);
        saveUserUseCase.save(user);
        return null;
    }
}
