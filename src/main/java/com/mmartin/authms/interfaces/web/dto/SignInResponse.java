package com.mmartin.authms.interfaces.web.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record SignInResponse(
        String username,
        String token
) {
}
