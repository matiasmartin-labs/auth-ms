package com.mmartin.authms.infrastructure.persistence.mapper;

import com.mmartin.authms.domain.model.User;
import com.mmartin.authms.domain.model.vo.Password;
import com.mmartin.authms.domain.model.vo.Username;
import com.mmartin.authms.infrastructure.persistence.entity.UserEntity;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Objects;

@ApplicationScoped
public class UserMapper {

    public User mapToModel(UserEntity src) {
        if (Objects.isNull(src)) {
            return null;
        }
        return User.builder()
                .username(new Username(src.getUsername()))
                .password(new Password(src.getPassword()))
                .build();
    }

    public UserEntity mapToEntity(User src) {
        if (Objects.isNull(src)) {
            return null;
        }
        return UserEntity.builder()
                .username(src.getId().value())
                .password(src.getPassword().value())
                .build();
    }
}
