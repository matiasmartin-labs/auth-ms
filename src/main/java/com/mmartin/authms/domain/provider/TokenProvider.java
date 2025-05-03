package com.mmartin.authms.domain.provider;

import com.mmartin.authms.domain.model.Authorization;
import com.mmartin.authms.domain.model.User;

public interface TokenProvider {

    String generate(final User user);

    void revoke(final Authorization authorization);

    static TokenProvider instance() {
        if (InstanceHolder.INSTANCE == null) {
            throw new IllegalStateException("TokenProvider not initialized");
        }

        return InstanceHolder.INSTANCE;
    }

    static void configure(final TokenProvider tokenProvider) {
        InstanceHolder.INSTANCE = tokenProvider;
    }

    class InstanceHolder {
        private static TokenProvider INSTANCE;
    }
}
