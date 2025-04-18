package com.mmartin.authms.domain.provider;

import com.mmartin.authms.domain.model.vo.Password;

public interface PasswordEncoderProvider {

    String encode(String password);

    Boolean check(Password raw, Password encoded);

    static PasswordEncoderProvider instance() {
        if (PasswordEncoderProvider.InstanceHolder.INSTANCE == null) {
            throw new IllegalStateException("PasswordEncoderProvider not initialized");
        }

        return PasswordEncoderProvider.InstanceHolder.INSTANCE;
    }

    static void configure(PasswordEncoderProvider passwordEncoderProvider) {
        PasswordEncoderProvider.InstanceHolder.INSTANCE = passwordEncoderProvider;
    }

    class InstanceHolder {
        private static PasswordEncoderProvider INSTANCE;
    }
}
