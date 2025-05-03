package com.mmartin.authms.application.usecase;

import com.mmartin.authms.domain.model.Authorization;
import com.mmartin.authms.domain.provider.TokenProvider;
import com.mmartin.authms.domain.usecase.LogoutUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
class LogoutUseCaseImpl implements LogoutUseCase {

    private final TokenProvider tokenProvider;

    @Override
    public void logout(Authorization authorization) {
        tokenProvider.revoke(authorization);
    }
}
