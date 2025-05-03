package com.mmartin.authms.domain.model;

import org.apache.commons.lang3.StringUtils;

public record Authorization(
        String token
) {

    public Authorization {
        if (StringUtils.isBlank(token)) {
            throw new IllegalArgumentException("token can't be empty");
        }
        String BEARER_PREFIX = "Bearer ";
        if (token.startsWith(BEARER_PREFIX)) {
            token = token.substring(BEARER_PREFIX.length()).trim();
        }
    }
}
