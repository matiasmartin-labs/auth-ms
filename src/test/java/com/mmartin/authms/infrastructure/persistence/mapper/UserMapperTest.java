package com.mmartin.authms.infrastructure.persistence.mapper;

import com.mmartin.authms.domain.model.User;
import com.mmartin.authms.domain.model.vo.Password;
import com.mmartin.authms.domain.model.vo.Username;
import com.mmartin.authms.infrastructure.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {

    @InjectMocks
    private UserMapper userMapper;

    @Test
    void given_entity_when_call_mapToModel_then_return_model() {
        final var entity = new UserEntity();
        entity.setId(UUID.randomUUID());
        entity.setUsername("username");
        entity.setPassword("password");

        final var actual = userMapper.mapToModel(entity);

        assertThat(actual)
                .isEqualTo(User.builder()
                        .username(new Username("username"))
                        .password(new Password("password"))
                        .build());
    }

    @Test
    void given_entity_null_when_call_mapToModel_then_return_model() {

        final var actual = userMapper.mapToModel(null);

        assertThat(actual)
                .isNull();
    }

    @Test
    void given_model_when_call_mapToEntity_then_return_entity() {
        final var model = User.builder()
                .username(new Username("username"))
                .password(new Password("password"))
                .build();
        final var entity = new UserEntity();
        entity.setUsername("username");
        entity.setPassword("password");

        final var actual = userMapper.mapToEntity(model);

        assertThat(actual)
                .isEqualTo(entity);
    }

    @Test
    void given_model_null_when_call_mapToEntity_then_return_entity() {

        final var actual = userMapper.mapToEntity(null);

        assertThat(actual)
                .isNull();
    }
}
