package com.test.application.user.service;

import com.test.application.user.models.entity.User;
import org.springframework.security.core.Authentication;

/**
 * Clase para JWT
 *
 * @author William Villaueva
 * @version 1.0.0
 */
public interface JwtTokenService {


    String generateToken(User user);

    Authentication getAuthentication(String token);

}

