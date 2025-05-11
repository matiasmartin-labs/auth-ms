package com.mmartin.authms.infrastructure.repository;

import com.mmartin.authms.domain.model.User;
import com.mmartin.authms.domain.model.vo.Username;
import com.mmartin.authms.infrastructure.persistence.UserRepository;
import com.mmartin.authms.infrastructure.persistence.entity.UserEntity;
import com.mmartin.authms.infrastructure.persistence.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserRepositoryImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserRepositoryImpl userRepositoryImpl;

    @Test
    void given_username_when_call_findUser_then_return_optional_with_value() {
        final var username = new Username("username");
        final var userEntity = mock(UserEntity.class);
        final var user = mock(User.class);

        when(this.userRepository.findByUsername("username")).thenReturn(Optional.of(userEntity));
        when(this.userMapper.mapToModel(userEntity)).thenReturn(user);

        final var actual = this.userRepositoryImpl.findUser(username);

        assertThat(actual)
                .contains(user);

        verify(this.userRepository).findByUsername("username");
        verify(this.userMapper).mapToModel(userEntity);
    }

    @Test
    void given_username_not_exists_when_call_findUser_then_return_optional_empty() {
        final var username = new Username("username");

        when(this.userRepository.findByUsername("username")).thenReturn(Optional.empty());

        final var actual = this.userRepositoryImpl.findUser(username);

        assertThat(actual)
                .isEmpty();

        verify(this.userRepository).findByUsername("username");
        verify(this.userMapper, never()).mapToModel(any());
    }

    @Test
    void given_username_when_call_existsUser_then_return_true() {
        final var username = new Username("username");

        when(this.userRepository.existsByUsername("username")).thenReturn(true);

        final var actual = this.userRepositoryImpl.exists(username);

        assertThat(actual)
                .isTrue();

        verify(this.userRepository).existsByUsername("username");
    }

    @Test
    void given_username_not_exists_when_call_existsUser_then_return_false() {
        final var username = new Username("username");

        when(this.userRepository.existsByUsername("username")).thenReturn(false);

        final var actual = this.userRepositoryImpl.exists(username);

        assertThat(actual)
                .isFalse();

        verify(this.userRepository).existsByUsername("username");
    }

    @Test
    void given_user_when_call_save_then_success_saved() {
        final var user = mock(User.class);
        final var userEntity = mock(UserEntity.class);

        when(this.userMapper.mapToEntity(user)).thenReturn(userEntity);

        this.userRepositoryImpl.save(user);

        verify(this.userMapper).mapToEntity(user);
        verify(this.userRepository).persist(userEntity);
    }
}
