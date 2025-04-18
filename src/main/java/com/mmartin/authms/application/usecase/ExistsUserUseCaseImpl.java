package com.mmartin.authms.application.usecase;

import com.mmartin.authms.domain.model.vo.Username;
import com.mmartin.authms.domain.repository.ExistsUserRepository;
import com.mmartin.authms.domain.usecase.ExistsUserUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
class ExistsUserUseCaseImpl implements ExistsUserUseCase {

    private final ExistsUserRepository existsUserRepository;

    @Override
    public boolean exists(Username username) {
        return existsUserRepository.exists(username);
    }
}
