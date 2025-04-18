package com.mmartin.authms.infrastructure.provider;

import com.mmartin.authms.domain.model.User;
import com.mmartin.authms.domain.provider.TokenProvider;
import io.quarkus.runtime.Startup;
import io.smallrye.jwt.build.Jwt;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@Startup
@ApplicationScoped
class JwtTokenProvider implements TokenProvider {

    @PostConstruct
    void init() {
        TokenProvider.configure(this);
    }

    @Override
    public String generate(User user) {
        return Jwt.issuer("auth-ms")
                .upn(user.getId().value())
                .claim("username", user.getId().value())
                .claim("groups", Set.of("USER"))
                .expiresAt(Instant.now().plus(1, ChronoUnit.HOURS))
                .sign();
    }
}
