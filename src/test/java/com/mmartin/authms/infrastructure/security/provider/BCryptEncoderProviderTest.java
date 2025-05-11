package com.mmartin.authms.infrastructure.security.provider;

import com.mmartin.authms.domain.model.vo.Password;
import com.mmartin.authms.domain.provider.PasswordEncoderProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
class BCryptEncoderProviderTest {

    @InjectMocks
    private BCryptEncoderProvider bCryptEncoderProvider;

    private MockedStatic<BCrypt> bCryptMockedStatic;

    @BeforeEach
    void setup() {
        this.bCryptMockedStatic = mockStatic(BCrypt.class);
    }

    @AfterEach
    void tearDown() {
        this.bCryptMockedStatic.close();
    }

    @Test
    void given_instance_when_crated_then_return_instance() {

        this.bCryptEncoderProvider.init();

        assertThat(this.bCryptEncoderProvider)
                .isEqualTo(PasswordEncoderProvider.instance());
    }

    @Test
    void given_raw_password_when_call_encode_then_return_encrypted_password() {
        final var somePassword = "password";
        final var someSalt = "salt";

        this.bCryptMockedStatic.when(BCrypt::gensalt).thenReturn(someSalt);
        this.bCryptMockedStatic.when(() -> BCrypt.hashpw(somePassword, someSalt)).thenReturn("encrypted-password");

        final var actual = bCryptEncoderProvider.encode(somePassword);

        assertThat(actual)
                .isEqualTo("encrypted-password");

        this.bCryptMockedStatic.verify(BCrypt::gensalt);
        this.bCryptMockedStatic.verify(() -> BCrypt.hashpw(somePassword, someSalt));
    }

    @Test
    void given_raw_password_and_encrypted_password_when_call_check_then_return_true() {
        final var somePassword = new Password("password");
        final var encryptedPassword = new Password("encrypted-password");

        this.bCryptMockedStatic.when(() -> BCrypt.checkpw(somePassword.value(), encryptedPassword.value())).thenReturn(true);

        final var actual = bCryptEncoderProvider.check(somePassword, encryptedPassword);

        assertThat(actual)
                .isTrue();

        this.bCryptMockedStatic.verify(() -> BCrypt.checkpw(somePassword.value(), encryptedPassword.value()));
    }

    @Test
    void given_raw_password_and_encrypted_password_when_call_check_then_return_false() {
        final var somePassword = new Password("password");
        final var encryptedPassword = new Password("encrypted-password");

        this.bCryptMockedStatic.when(() -> BCrypt.checkpw(somePassword.value(), encryptedPassword.value())).thenReturn(false);

        final var actual = bCryptEncoderProvider.check(somePassword, encryptedPassword);

        assertThat(actual)
                .isFalse();

        this.bCryptMockedStatic.verify(() -> BCrypt.checkpw(somePassword.value(), encryptedPassword.value()));
    }
}
