package com.mmartin.authms.infrastructure.security.provider;

import com.mmartin.authms.domain.exception.InvalidCredentialsException;
import com.mmartin.authms.domain.model.Authorization;
import com.mmartin.authms.domain.model.User;
import com.mmartin.authms.domain.provider.TokenProvider;
import com.mmartin.authms.domain.repository.RevokeTokenRepository;
import io.quarkus.runtime.Startup;
import io.smallrye.jwt.auth.principal.JWTParser;
import io.smallrye.jwt.auth.principal.ParseException;
import io.smallrye.jwt.build.Jwt;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Set;

@Startup
@ApplicationScoped
@RequiredArgsConstructor
class JwtTokenProvider implements TokenProvider {

    private static final String JTI_CLAIM = "jti";

    private final JWTParser jwtParser;

    private final RevokeTokenRepository revokeTokenRepository;

    @PostConstruct
    void init() {
        TokenProvider.configure(this);
    }

    @Override
    public String generate(final User user) {
        return Jwt.issuer("auth-ms")
                .upn(user.getId().value())
                .claim("username", user.getId().value())
                .claim("groups", Set.of("USER"))
                .expiresAt(Instant.now().plus(1, ChronoUnit.HOURS))
                .sign();
    }

    @Override
    public void revoke(Authorization authorization) {
        try {
            final JsonWebToken jwt = this.jwtParser.parse(authorization.token());
            Optional<String> jtiOpt =  jwt.claim(JTI_CLAIM);
            jtiOpt.ifPresent(jti -> this.revokeTokenRepository.revoke(jti, jwt.getExpirationTime()));
        } catch (ParseException e) {
            throw new InvalidCredentialsException();
        }
    }

    @Override
    public void validate(Authorization authorization) {
        try {
            final JsonWebToken jwt = this.jwtParser.parse(authorization.token());
            Optional<String> jtiOpt =  jwt.claim(JTI_CLAIM);
            if (jtiOpt.isEmpty()) {
                throw new InvalidCredentialsException();
            }

            final String jti = jtiOpt.get();
            if (this.revokeTokenRepository.isRevoked(jti)) {
                throw new InvalidCredentialsException();
            }

        } catch (ParseException e) {
            throw new InvalidCredentialsException();
        }
    }
}
