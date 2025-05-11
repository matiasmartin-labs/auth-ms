package com.mmartin.authms.domain.model;

import org.apache.commons.lang3.StringUtils;

public record Authorization(
        String token
) {

    public Authorization {
        if (StringUtils.isBlank(token)) {
            throw new IllegalArgumentException("token can't be empty");
        }

        String bearerPrefix = "Bearer ";
        if (token.startsWith(bearerPrefix)) {
            token = token.substring(bearerPrefix.length()).trim();
        }
    }
}
