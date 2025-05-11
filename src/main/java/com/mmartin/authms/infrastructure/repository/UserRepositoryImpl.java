package com.mmartin.authms.infrastructure.repository;

import com.mmartin.authms.domain.model.User;
import com.mmartin.authms.domain.model.vo.Username;
import com.mmartin.authms.domain.repository.ExistsUserRepository;
import com.mmartin.authms.domain.repository.FindUserRepository;
import com.mmartin.authms.domain.repository.SaveUserRepository;
import com.mmartin.authms.infrastructure.persistence.UserRepository;
import com.mmartin.authms.infrastructure.persistence.entity.UserEntity;
import com.mmartin.authms.infrastructure.persistence.mapper.UserMapper;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
class UserRepositoryImpl implements SaveUserRepository, ExistsUserRepository, FindUserRepository {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void save(User user) {
        final UserEntity entity = userMapper.mapToEntity(user);
        this.userRepository.persist(entity);
    }

    @Override
    public boolean exists(Username username) {
        return this.userRepository.existsByUsername(username.value());
    }

    @Override
    public Optional<User> findUser(Username username) {
        return this.userRepository.findByUsername(username.value())
                .map(this.userMapper::mapToModel);
    }
}
