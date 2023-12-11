package com.test.application.user.service.impl;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.test.application.config.ApplicationProperties;
import com.test.application.dto.PhoneRequest;
import com.test.application.dto.UserRequest;
import com.test.application.dto.UserResponse;
import com.test.application.user.util.exception.DataValidationException;
import com.test.application.user.models.entity.User;
import com.test.application.user.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Spy
    ApplicationProperties properties;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userServiceImpl;

    String authorization = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJNYXJ0aW4gR2FyY2lhIiwicm9sZSI6IlVTRVIiLCJleHAiOjE2OTk3NTMzMDksImlhdCI6MTY5OTc1MzAwOX0.01eHoATkfmi_tOmiXGNQI9Qr-zZhRn_xOp_GeextaCvxyU9RwKxYoVH62PIi5HQFKGPZH5qhOjY_OVxv_4EmZA";

    UserRequest userRequest;
    User user;
    String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJNYXJ0aW4gR2FyY2lhIiwicm9sZSI6IlVTRVIiLCJleHAiOjE2OTk3NTMzMDksImlhdCI6MTY5OTc1MzAwOX0.01eHoATkfmi_tOmiXGNQI9Qr-zZhRn_xOp_GeextaCvxyU9RwKxYoVH62PIi5HQFKGPZH5qhOjY_OVxv_4EmZA";

    @BeforeEach
    void setUp() {
        userRequest = UserRequest.builder()
                .name("Miguel Torres Rodriguez")
                .email("mtorres@dominio.cl")
                .password("Juan1234.")
                .phones(List.of(PhoneRequest.builder()
                        .number("555511122")
                        .cityCode("1")
                        .countryCode("51")
                        .build()))
                .build();

        user = User.builder()
                .id("232323-2232322344234-5ffgcvfdfkk-7vv")
                .created(new Date())
                .modified(null)
                .lastLogin(new Date())
                .token("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsdWlzcGVyZXpAbmlrZS5jbCIsInJvbGUiOiJVU0VSIiwiZXhwIjoxNzAyMTk2NDM3LCJpYXQiOjE3MDIxOTYxMzd9.BQMMP5bhh3jMpinER2fF6T0ljOcszEwD4SuIjsz22jwlGec7wf5B8TUOW08o-JwpIPpwN9uMztO7mFVwEDCVWw")
                .isActive(true)
                .build();

        properties.setEmailRegex("^[a-zA-Z0-9]+[\\w\\-\\.]*@[a-zA-Z0-9]+[\\w\\-]*[\\.]cl$");
        properties.setPasswordRegex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&\\.\\-\\_])[A-Za-z\\d@$!%?&\\.\\-\\_]{8,20}$");
    }

    @Test
    @DisplayName("Save user when is successful")
    void saveUserWhenIsSuccessful() {
        when(userRepository.findUserByEmail(anyString())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);
        UserResponse userResponse = userServiceImpl.saveUser(authorization, userRequest);
        assertNotNull(userResponse);
    }

    @Test
    @DisplayName("Return exception when email is invalid")
    void returnExceptionWhenEmailIsInvalid() {
        userRequest.setEmail("mtorres@pe");
        assertThrows(DataValidationException.class, () -> userServiceImpl.saveUser(authorization, userRequest));
    }

    @Test
    @DisplayName("Return exception when password is invalid")
    void returnExceptionWhenPasswordIsInvalid() {
        userRequest.setPassword("miguel");
        assertThrows(DataValidationException.class, () -> userServiceImpl.saveUser(authorization, userRequest));
    }

    @Test
    @DisplayName("Return exception when email already exists")
    void returnExceptionWhenEmailAlreadyExists() {
        when(userRepository.findUserByEmail(anyString())).thenReturn(Optional.of(new User()));
        assertThrows(DataValidationException.class, () -> userServiceImpl.saveUser(authorization, userRequest));
    }

    @Test
    @DisplayName("Update user when is successful")
    void updateUserWhenIsSuccessful() {
        when(userRepository.save(any(User.class))).thenReturn(user);
        User userResponse = userServiceImpl.updateUserSession(user, token);
        assertNotNull(userResponse);
    }

}
