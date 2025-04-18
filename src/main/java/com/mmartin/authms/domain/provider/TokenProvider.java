package com.mmartin.authms.domain.provider;

import com.mmartin.authms.domain.model.User;
import com.mmartin.authms.domain.model.vo.Username;

public interface TokenProvider {

    String generate(User user);

    static TokenProvider instance() {
        if (InstanceHolder.INSTANCE == null) {
            throw new IllegalStateException("TokenProvider not initialized");
        }

        return InstanceHolder.INSTANCE;
    }

    static void configure(TokenProvider tokenProvider) {
        InstanceHolder.INSTANCE = tokenProvider;
    }

    class InstanceHolder {
        private static TokenProvider INSTANCE;
    }
}
