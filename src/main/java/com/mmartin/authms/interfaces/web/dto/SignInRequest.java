package com.mmartin.authms.interfaces.web.dto;

public record SignInRequest(
        String username,
        String password
) {
}
