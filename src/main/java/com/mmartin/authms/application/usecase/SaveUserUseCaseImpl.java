package com.mmartin.authms.application.usecase;

import com.mmartin.authms.domain.model.User;
import com.mmartin.authms.domain.repository.SaveUserRepository;
import com.mmartin.authms.domain.usecase.SaveUserUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@ApplicationScoped
@RequiredArgsConstructor
class SaveUserUseCaseImpl implements SaveUserUseCase {

    private final SaveUserRepository saveUserRepository;

    @Override
    public void save(final User user) {
        if (Objects.isNull(user)) {
            throw new IllegalArgumentException("user can't be null");
        }
        this.saveUserRepository.save(user);
    }
}
