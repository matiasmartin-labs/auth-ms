package com.mmartin.authms.infrastructure.presentation.dto;

public record SignInRequest(
        String username,
        String password
) {
}
