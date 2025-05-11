package com.mmartin.authms.domain.model;

import com.mmartin.authms.application.command.SignUpCommand;
import com.mmartin.authms.domain.event.UserRegistered;
import com.mmartin.authms.domain.exception.InvalidCredentialsException;
import com.mmartin.authms.domain.model.vo.EncodedPassword;
import com.mmartin.authms.domain.model.vo.Password;
import com.mmartin.authms.domain.model.vo.Username;
import com.mmartin.authms.domain.provider.PasswordEncoderProvider;
import com.mmartin.authms.domain.provider.TokenProvider;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class User extends AggregateRoot<Username> {

    Password password;

    @Builder
    private User(Username username, Password password) {
        super(username);
        this.password = password;
    }

    public static User signUp(final SignUpCommand command) {
        final Username username = new Username(command.username());
        final Password password = new EncodedPassword(command.password());
        final User user = User.builder()
                .username(username)
                .password(password)
                .build();

        user.registerEvent(new UserRegistered(username));

        return user;
    }

    public Authorization signIn(Password password) {
        if (this.notIsValidPassword(password)) {
            throw new InvalidCredentialsException();
        }

        final String token = TokenProvider.instance()
                .generate(this);

        return new Authorization(token);
    }

    private boolean notIsValidPassword(Password password) {
        return !this.isValidPassword(password);
    }

    private boolean isValidPassword(Password password) {
        return PasswordEncoderProvider.instance().check(password, this.password);
    }
}
