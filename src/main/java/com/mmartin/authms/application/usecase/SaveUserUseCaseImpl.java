package com.mmartin.authms.application.usecase;

import com.mmartin.authms.domain.model.User;
import com.mmartin.authms.domain.repository.SaveUserRepository;
import com.mmartin.authms.domain.usecase.SaveUserUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
class SaveUserUseCaseImpl implements SaveUserUseCase {

    private final SaveUserRepository saveUserRepository;

    @Override
    public void save(User user) {
        saveUserRepository.save(user);
    }
}
