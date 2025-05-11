package com.mmartin.authms.infrastructure.repository;

import com.mmartin.authms.domain.repository.RevokeTokenRepository;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.value.ValueCommands;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.commons.lang3.StringUtils;

@ApplicationScoped
class TokenRepositoryImpl implements RevokeTokenRepository {

    private final ValueCommands<String, String> valueCommands;

    public TokenRepositoryImpl(final RedisDataSource ds) {
        this.valueCommands = ds.value(String.class, String.class);
    }

    @Override
    public void revoke(final String jti, final Long expirationTime) {
        final long ttl = expirationTime - (System.currentTimeMillis() / 1000);
        this.valueCommands.setex(jti, ttl, "revoked");
    }

    @Override
    public boolean isRevoked(String jti) {
        final String value = this.valueCommands.get(jti);
        return StringUtils.isNoneBlank(value);
    }

}
