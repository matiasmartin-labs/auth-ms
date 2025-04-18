package com.mmartin.authms.infrastructure.persistence;

import com.mmartin.authms.domain.model.User;
import com.mmartin.authms.infrastructure.persistence.entity.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class UserRepository implements PanacheRepositoryBase<UserEntity, String> {

    public boolean existsByUsername(String username) {
        return find("username", username)
                .firstResultOptional()
                .isPresent();
    }

    public Optional<UserEntity> findByUsername(String username) {
        return find("username", username)
                .firstResultOptional();
    }
}
