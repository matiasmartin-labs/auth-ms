package com.mmartin.authms.interfaces.web.dto;

public record SignUpRequest(
        String username,
        String password
) {
}
