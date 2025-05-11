package com.mmartin.authms.application.usecase;

import com.mmartin.authms.domain.model.User;
import com.mmartin.authms.domain.model.vo.Username;
import com.mmartin.authms.domain.repository.FindUserRepository;
import com.mmartin.authms.domain.usecase.FindUserUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
class FindUserUseCaseImpl implements FindUserUseCase {

    private final FindUserRepository findUserRepository;

    @Override
    public Optional<User> findUser(final Username username) {
        if (Objects.isNull(username)) {
            throw new IllegalArgumentException("username can't be null");
        }
        return this.findUserRepository.findUser(username);
    }
}
