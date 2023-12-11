package com.test.application.user.delegate;

import com.test.application.user.models.entity.User;
import com.test.application.user.service.AuthService;
import com.test.application.user.service.JwtTokenService;
import com.test.application.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.test.application.api.AuthApiDelegate;
import com.test.application.dto.AuthRequest;
import com.test.application.dto.JwtResponse;

/**
 * Clase para autenticación
 *
 * @author William Villaueva
 * @version 1.0.0
 */
@Service
public class AuthDelegateImpl implements AuthApiDelegate {

    private AuthService authService;
    private JwtTokenService jwtTokenService;
    private UserService userService;

    public AuthDelegateImpl(AuthService authService, JwtTokenService jwtTokenService,
                            UserService userService) {
        this.authService = authService;
        this.jwtTokenService = jwtTokenService;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<JwtResponse> authenticate(AuthRequest authRequest) {
        User user = authService.getUser(authRequest);
        String jwt = jwtTokenService.generateToken(user);
        userService.updateUserSession(user, jwt);
        JwtResponse response = JwtResponse.builder().jwt(jwt).build();
        return new ResponseEntity<JwtResponse>(response, HttpStatus.OK);
    }

}

