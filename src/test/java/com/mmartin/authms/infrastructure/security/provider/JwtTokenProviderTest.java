package com.mmartin.authms.infrastructure.security.provider;

import com.mmartin.authms.domain.exception.InvalidCredentialsException;
import com.mmartin.authms.domain.model.Authorization;
import com.mmartin.authms.domain.model.User;
import com.mmartin.authms.domain.model.vo.Username;
import com.mmartin.authms.domain.provider.TokenProvider;
import com.mmartin.authms.domain.repository.RevokeTokenRepository;
import io.smallrye.jwt.auth.principal.JWTParser;
import io.smallrye.jwt.auth.principal.ParseException;
import io.smallrye.jwt.build.JwtClaimsBuilder;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import io.smallrye.jwt.build.Jwt;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtTokenProviderTest {

    private MockedStatic<Jwt> jwtMockedStatic;

    private Instant instant;

    private MockedStatic<Instant> instantMockedStatic;

    @Mock
    private JWTParser jwtParser;

    @Mock
    private RevokeTokenRepository revokeTokenRepository;

    @InjectMocks
    private JwtTokenProvider jwtTokenProvider;

    @BeforeEach
    void setup() {
        this.instant = Instant.now();
        this.instantMockedStatic = mockStatic(Instant.class);
        this.jwtMockedStatic = mockStatic(Jwt.class);
    }

    @AfterEach
    void tearDown() {
        this.jwtMockedStatic.close();
        this.instantMockedStatic.close();
    }

    @Test
    void given_instance_when_crated_then_return_instance() {

        this.jwtTokenProvider.init();

        assertThat(this.jwtTokenProvider)
                .isEqualTo(TokenProvider.instance());
    }

    @Test
    void given_user_when_call_generate_then_return_token() {
        final var claimsBuilder = mock(JwtClaimsBuilder.class);
        final var user = mock(User.class);
        final var userId = mock(Username.class);

        this.jwtMockedStatic.when(() -> Jwt.issuer("auth-ms")).thenReturn(claimsBuilder);
        this.instantMockedStatic.when(Instant::now).thenReturn(this.instant);
        when(user.getId()).thenReturn(userId);
        when(userId.value()).thenReturn("user-id");
        when(claimsBuilder.upn("user-id")).thenReturn(claimsBuilder);
        when(claimsBuilder.claim("username", "user-id")).thenReturn(claimsBuilder);
        when(claimsBuilder.claim("groups", Set.of("USER"))).thenReturn(claimsBuilder);
        when(claimsBuilder.expiresAt(instant.plus(1, ChronoUnit.HOURS))).thenReturn(claimsBuilder);
        when(claimsBuilder.sign()).thenReturn("super-token");

        final var actual = this.jwtTokenProvider.generate(user);

        assertThat(actual)
                .isEqualTo("super-token");

        this.jwtMockedStatic.verify(() -> Jwt.issuer("auth-ms"));
        this.instantMockedStatic.verify(Instant::now);
        verify(user, times(2)).getId();
        verify(userId, times(2)).value();
        verify(claimsBuilder).upn("user-id");
        verify(claimsBuilder).claim("username", "user-id");
        verify(claimsBuilder).claim("groups", Set.of("USER"));
        verify(claimsBuilder).expiresAt(instant.plus(1, ChronoUnit.HOURS));
        verify(claimsBuilder).sign();
    }

    @Test
    void given_authorization_when_call_revoke_then_success_revoked() throws ParseException {
        final var token = "super-token";
        final var jwt = mock(JsonWebToken.class);
        final var authorization = new Authorization(token);

        when(this.jwtParser.parse(token)).thenReturn(jwt);
        when(jwt.claim("jti")).thenReturn(Optional.of("super-jti"));
        when(jwt.getExpirationTime()).thenReturn(1000L);

        this.jwtTokenProvider.revoke(authorization);

        verify(this.revokeTokenRepository).revoke("super-jti", 1000L);
    }

    @Test
    void given_authorization_empty_jti_when_call_revoke_then_success_revoked() throws ParseException {
        final var token = "super-token";
        final var jwt = mock(JsonWebToken.class);
        final var authorization = new Authorization(token);

        when(this.jwtParser.parse(token)).thenReturn(jwt);
        when(jwt.claim("jti")).thenReturn(Optional.empty());

        this.jwtTokenProvider.revoke(authorization);

        verify(this.revokeTokenRepository, never()).revoke(anyString(), anyLong());
    }

    @Test
    void given_authorization_parse_error_when_call_revoke_then_throw_exception() throws ParseException {
        final var token = "super-token";
        final var authorization = new Authorization(token);

        when(this.jwtParser.parse(token)).thenThrow(ParseException.class);

        assertThatThrownBy(() -> this.jwtTokenProvider.revoke(authorization))
                .isInstanceOf(InvalidCredentialsException.class);

        verify(this.revokeTokenRepository, never()).revoke(anyString(), anyLong());
    }

    @Test
    void given_authorization_when_call_validate_then_throw_exception() throws ParseException {
        final var token = "super-token";
        final var jwt = mock(JsonWebToken.class);
        final var authorization = new Authorization(token);

        when(this.jwtParser.parse(token)).thenReturn(jwt);
        when(jwt.claim("jti")).thenReturn(Optional.of("super-jti"));
        when(this.revokeTokenRepository.isRevoked("super-jti")).thenReturn(true);

        assertThatThrownBy(() -> this.jwtTokenProvider.validate(authorization))
                .isInstanceOf(InvalidCredentialsException.class);

        verify(this.revokeTokenRepository).isRevoked("super-jti");
    }

    @Test
    void given_authorization_jti_empty_when_call_validate_then_throw_exception() throws ParseException {
        final var token = "super-token";
        final var jwt = mock(JsonWebToken.class);
        final var authorization = new Authorization(token);

        when(this.jwtParser.parse(token)).thenReturn(jwt);
        when(jwt.claim("jti")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> this.jwtTokenProvider.validate(authorization))
                .isInstanceOf(InvalidCredentialsException.class);

        verify(this.revokeTokenRepository, never()).isRevoked(anyString());
    }

    @Test
    void given_authorization_parse_exception_when_call_validate_then_throw_exception() throws ParseException {
        final var token = "super-token";
        final var authorization = new Authorization(token);

        when(this.jwtParser.parse(token)).thenThrow(ParseException.class);

        assertThatThrownBy(() -> this.jwtTokenProvider.validate(authorization))
                .isInstanceOf(InvalidCredentialsException.class);

        verify(this.revokeTokenRepository, never()).isRevoked(anyString());
    }

    @Test
    void given_authorization_when_call_validate_then_success_validation() throws ParseException {
        final var token = "super-token";
        final var jwt = mock(JsonWebToken.class);
        final var authorization = new Authorization(token);

        when(this.jwtParser.parse(token)).thenReturn(jwt);
        when(jwt.claim("jti")).thenReturn(Optional.of("super-jti"));
        when(this.revokeTokenRepository.isRevoked("super-jti")).thenReturn(false);

        this.jwtTokenProvider.validate(authorization);

        verify(this.revokeTokenRepository).isRevoked("super-jti");
    }
}
