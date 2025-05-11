package com.mmartin.authms.application.usecase;

import com.mmartin.authms.domain.model.Authorization;
import com.mmartin.authms.domain.provider.TokenProvider;
import com.mmartin.authms.domain.usecase.ValidateTokenUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@ApplicationScoped
@RequiredArgsConstructor
class ValidateTokenUseCaseImpl implements ValidateTokenUseCase {

    private final TokenProvider tokenProvider;

    @Override
    public void validate(Authorization authorization) {
        if (Objects.isNull(authorization)) {
            throw new IllegalArgumentException("authorization can't be null");
        }
        this.tokenProvider.validate(authorization);
    }
}
