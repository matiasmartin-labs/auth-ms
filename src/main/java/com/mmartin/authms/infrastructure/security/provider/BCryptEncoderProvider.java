package com.mmartin.authms.infrastructure.security.provider;

import com.mmartin.authms.domain.model.vo.Password;
import com.mmartin.authms.domain.provider.PasswordEncoderProvider;
import io.quarkus.runtime.Startup;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import org.mindrot.jbcrypt.BCrypt;

@Startup
@ApplicationScoped
class BCryptEncoderProvider implements PasswordEncoderProvider {

    @PostConstruct
    void init() {
        PasswordEncoderProvider.configure(this);
    }

    @Override
    public String encode(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public Boolean check(Password raw, Password encoded) {
        return BCrypt.checkpw(raw.value(), encoded.value());
    }
}
