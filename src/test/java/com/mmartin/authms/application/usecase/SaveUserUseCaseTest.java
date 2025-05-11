package com.mmartin.authms.application.usecase;

import com.mmartin.authms.domain.model.User;
import com.mmartin.authms.domain.repository.SaveUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SaveUserUseCaseTest {

    @Mock
    private SaveUserRepository saveUserRepository;

    @InjectMocks
    private SaveUserUseCaseImpl saveUserUseCase;

    @Test
    void given_user_when_call_exists_then_success() {
        final var user = mock(User.class);

        saveUserUseCase.save(user);

        verify(saveUserRepository).save(user);
    }

    @Test
    void given_user_null_when_call_save_then_throw_exception() {

        assertThatThrownBy(() -> saveUserUseCase.save(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("user can't be null");

        verify(saveUserRepository, never()).save(any());
    }
}
