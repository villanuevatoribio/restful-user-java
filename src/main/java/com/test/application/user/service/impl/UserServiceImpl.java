package com.test.application.user.service.impl;

import java.util.Date;

import com.test.application.config.ApplicationProperties;
import com.test.application.user.models.entity.Phone;
import com.test.application.user.models.entity.User;
import com.test.application.user.service.UserService;
import com.test.application.user.util.Utils;
import com.test.application.user.util.constans.Constants;
import com.test.application.user.util.exception.DataValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.application.dto.UserRequest;
import com.test.application.dto.UserResponse;
import com.test.application.user.repository.UserRepository;

/**
 * Implementación de la  clase UserService
 *
 * @author William Villaueva
 * @version 1.0.0
 */
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ApplicationProperties properties;


    public UserServiceImpl(UserRepository userRepository, ApplicationProperties properties) {
        this.userRepository = userRepository;
        this.properties = properties;
    }


    @Override
    @Transactional
    public UserResponse saveUser(String authorization, UserRequest userRequest) {
        if (Utils.isInvalidString(properties.getEmailRegex(), userRequest.getEmail())) {
            throw new DataValidationException(Constants.INVALID_EMAIL_MESSAGE,
                    HttpStatus.BAD_REQUEST);
        }
        if (Utils.isInvalidString(properties.getPasswordRegex(), userRequest.getPassword())) {
            throw new DataValidationException(Constants.INVALID_PASSWORD_MESSAGE,
                    HttpStatus.BAD_REQUEST);
        }
        if (userRepository.findUserByEmail(userRequest.getEmail()).isPresent()) {
            throw new DataValidationException(Constants.EMAIL_CONFLICT_MESSAGE,
                    HttpStatus.CONFLICT);
        }
        return mapUserToUserResponse(
                userRepository.save(mapUserRequestToUser(authorization, userRequest)));
    }


    private User mapUserRequestToUser(String authorization, UserRequest userRequest) {
        Date now = new Date();
        return User.builder()
                .id(Utils.getNewUuid())
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .created(now)
                .lastLogin(now)
                .token(authorization)
                .isActive(Boolean.TRUE)
                .phones(userRequest.getPhones().stream()
                        .map(phoneRequest -> Phone.builder()
                                .id(Utils.getNewUuid())
                                .number(phoneRequest.getNumber())
                                .cityCode(phoneRequest.getCityCode())
                                .countryCode(phoneRequest.getCountryCode())
                                .created(now)
                                .build())
                        .toList())
                .build();
    }

    private UserResponse mapUserToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .created(Utils.convertDateToFormattedString(user.getCreated(),
                        Constants.FORMAT_YYYY_MM_DD))
                .modified(Utils.convertDateToFormattedString(user.getModified(),
                        Constants.FORMAT_YYYY_MM_DD))
                .lastLogin(Utils.convertDateToFormattedString(user.getLastLogin(),
                        Constants.FORMAT_YYYY_MM_DD))
                .token(user.getToken())
                .isActive(user.getIsActive())
                .build();
    }


    @Override
    @Transactional
    public User updateUserSession(User user, String token) {
        user.setLastLogin(new Date());
        user.setToken(Constants.PREFIX_BEARER.concat(token));
        return userRepository.save(user);
    }

}
