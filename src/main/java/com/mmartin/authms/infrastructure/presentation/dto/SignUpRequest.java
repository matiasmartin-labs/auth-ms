package com.mmartin.authms.infrastructure.presentation.dto;

public record SignUpRequest(
        String username,
        String password
) {
}
