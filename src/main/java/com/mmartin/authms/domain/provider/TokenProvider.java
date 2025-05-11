package com.mmartin.authms.domain.provider;

import com.mmartin.authms.domain.model.Authorization;
import com.mmartin.authms.domain.model.User;

public interface TokenProvider {

    String generate(final User user);

    void revoke(final Authorization authorization);

    void validate(final Authorization authorization);

    static TokenProvider instance() {
        if (InstanceHolder.instance == null) {
            throw new IllegalStateException("TokenProvider not initialized");
        }

        return InstanceHolder.instance;
    }

    static void configure(final TokenProvider tokenProvider) {
        InstanceHolder.instance = tokenProvider;
    }

    class InstanceHolder {
        private static TokenProvider instance;

        private InstanceHolder() {}
    }
}
