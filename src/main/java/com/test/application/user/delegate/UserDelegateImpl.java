package com.test.application.user.delegate;

import com.test.application.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.test.application.api.UserApiDelegate;
import com.test.application.dto.UserRequest;
import com.test.application.dto.UserResponse;

/**
 * Clase para autorización
 *
 * @author William Villaueva
 * @version 1.0.0
 */
@Service
public class UserDelegateImpl implements UserApiDelegate {

    private final UserService userService;


    public UserDelegateImpl(UserService userService) {
        this.userService = userService;
    }


    @Override
    public ResponseEntity<UserResponse> saveUser(String authorization, UserRequest userRequest) {
        return new ResponseEntity<UserResponse>(userService.saveUser(authorization, userRequest),
                HttpStatus.CREATED);
    }

}