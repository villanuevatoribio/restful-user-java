package com.test.application.user.service;

import com.test.application.dto.UserRequest;
import com.test.application.dto.UserResponse;
import com.test.application.user.models.entity.User;

/**
 * Clase de service del Usuario
 *
 * @author William Villaueva
 * @version 1.0.0
 */
public interface UserService {

    UserResponse saveUser(String authorization, UserRequest userRequest);

    User updateUserSession(User user, String token);

}
