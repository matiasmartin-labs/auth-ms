package com.mmartin.authms.infrastructure.presentation.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record SignInResponse(
        String username,
        String token
) {
}
