package com.test.application.user.service.impl;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.test.application.dto.AuthRequest;
import com.test.application.user.util.exception.AuthenticationException;
import com.test.application.user.models.entity.User;
import com.test.application.user.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class AuthServiceImplTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    AuthServiceImpl authServiceImpl;

    AuthRequest authRequest;

    @BeforeEach
    void setUp() {
        authRequest = AuthRequest.builder()
                .email("sleyva@dominio.cl")
                .password("Lala1234%")
                .build();
    }

    @Test
    @DisplayName("Get user when is successful")
    void getUserWhenIsSuccessful() {
        when(userRepository.findUserByEmail(anyString()))
                .thenReturn(Optional.of(User.builder()
                        .isActive(true)
                        .password("Lala1234%")
                        .build()));

        User user = authServiceImpl.getUser(authRequest);

        assertNotNull(user);
    }

    @Test
    @DisplayName("Return exception when user is inactive")
    void returnExceptionWhenUserIsInactive() {
        when(userRepository.findUserByEmail(anyString()))
                .thenReturn(Optional.of(User.builder()
                        .isActive(false)
                        .password("Lala1234%")
                        .build()));

        assertThrows(AuthenticationException.class, () -> authServiceImpl.getUser(authRequest));
    }

}
