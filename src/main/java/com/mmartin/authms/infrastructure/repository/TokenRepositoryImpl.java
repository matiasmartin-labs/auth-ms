package com.mmartin.authms.infrastructure.repository;

import com.mmartin.authms.domain.repository.RevokeTokenRepository;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.value.ValueCommands;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
class TokenRepositoryImpl implements RevokeTokenRepository {

    private final ValueCommands<String, String> valueCommands;

    public TokenRepositoryImpl(final RedisDataSource ds) {
        this.valueCommands = ds.value(String.class, String.class);
    }

    @Override
    public void revoke(final String jti, final Long expirationTime) {
        final long ttl = expirationTime - (System.currentTimeMillis() / 1000);
        valueCommands.setex(jti, ttl, "revoke");
    }

}
