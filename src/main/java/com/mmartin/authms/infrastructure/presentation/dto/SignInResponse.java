package com.mmartin.authms.infrastructure.presentation.dto;

public record SignInResponse(
        String username,
        String token
) {
}
