package com.mmartin.authms.domain.repository;

public interface RevokeTokenRepository {

    void revoke(final String jti, final Long expirationTime);

    boolean isRevoked(final String jti);
}
