package com.test.application.user.service;

import com.test.application.dto.AuthRequest;
import com.test.application.user.models.entity.User;

/**
 * Clase para la autenticación
 *
 * @author William Villaueva
 * @version 1.0.0
 */
public interface AuthService {

    User getUser(AuthRequest authRequest);

}
