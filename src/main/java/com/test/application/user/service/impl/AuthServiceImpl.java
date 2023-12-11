package com.test.application.user.service.impl;


import com.test.application.user.models.entity.User;
import com.test.application.user.util.constans.Constants;
import com.test.application.user.util.exception.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.test.application.dto.AuthRequest;
import com.test.application.user.service.AuthService;
import com.test.application.user.repository.UserRepository;

/**
 * Implementación de la clase AuthService
 *
 * @author William Villaueva
 * @version 1.0.0
 */
@Service
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(AuthRequest authRequest) {
        return userRepository.findUserByEmail(authRequest.getEmail())
                .filter(user -> user.getIsActive())
                .filter(user -> authRequest.getPassword().equals(user.getPassword()))
                .orElseThrow(() -> new AuthenticationException(
                        Constants.INVALID_AUTHENTICATION_MESSAGE,
                        HttpStatus.UNAUTHORIZED));
    }

}
