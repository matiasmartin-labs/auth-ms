package com.mmartin.authms.application.usecase;

import com.mmartin.authms.domain.model.vo.Username;
import com.mmartin.authms.domain.repository.ExistsUserRepository;
import com.mmartin.authms.domain.usecase.ExistsUserUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@ApplicationScoped
@RequiredArgsConstructor
class ExistsUserUseCaseImpl implements ExistsUserUseCase {

    private final ExistsUserRepository existsUserRepository;

    @Override
    public boolean exists(final Username username) {
        if (Objects.isNull(username)) {
            throw new IllegalArgumentException("username can't be null");
        }
        return this.existsUserRepository.exists(username);
    }
}
