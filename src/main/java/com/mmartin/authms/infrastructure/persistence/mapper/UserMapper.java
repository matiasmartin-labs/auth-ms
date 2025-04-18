package com.mmartin.authms.infrastructure.persistence.mapper;

import com.mmartin.authms.domain.model.User;
import com.mmartin.authms.domain.model.vo.Password;
import com.mmartin.authms.domain.model.vo.Username;
import com.mmartin.authms.infrastructure.persistence.entity.UserEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserMapper {

    public User mapToModel(UserEntity src) {
        return User.builder()
                .username(new Username(src.getUsername()))
                .password(new Password(src.getPassword()))
                .build();
    }

    public UserEntity mapToEntity(User src) {
        return UserEntity.builder()
                .username(src.getId().value())
                .password(src.getPassword().value())
                .build();
    }
}
