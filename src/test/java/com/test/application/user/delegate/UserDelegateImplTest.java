package com.test.application.user.delegate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.test.application.dto.UserRequest;
import com.test.application.dto.UserResponse;
import com.test.application.user.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserDelegateImplTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserDelegateImpl userDelegateImpl;

    String authorization = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsdWlzcGVyZXpAbmlrZS5jbCIsInJvbGUiOiJVU0VSIiwiZXhwIjoxNzAyMTk2NDM3LCJpYXQiOjE3MDIxOTYxMzd9.BQMMP5bhh3jMpinER2fF6T0ljOcszEwD4SuIjsz22jwlGec7wf5B8TUOW08o-JwpIPpwN9uMztO7mFVwEDCVWw";

    @Test
    @DisplayName("Save user when is successful")
    void SaveUserWhenIsSuccessful() {
        when(userService.saveUser(anyString(), any(UserRequest.class))).thenReturn(new UserResponse());

        ResponseEntity<UserResponse> response = userDelegateImpl.saveUser(authorization, new UserRequest());

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

}