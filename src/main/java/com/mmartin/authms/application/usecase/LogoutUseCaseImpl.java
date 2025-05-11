package com.mmartin.authms.application.usecase;

import com.mmartin.authms.domain.model.Authorization;
import com.mmartin.authms.domain.provider.TokenProvider;
import com.mmartin.authms.domain.usecase.LogoutUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@ApplicationScoped
@RequiredArgsConstructor
class LogoutUseCaseImpl implements LogoutUseCase {

    private final TokenProvider tokenProvider;

    @Override
    public void logout(Authorization authorization) {
        if (Objects.isNull(authorization)) {
            throw new IllegalArgumentException("authorization can't be null");
        }
        this.tokenProvider.revoke(authorization);
    }
}
