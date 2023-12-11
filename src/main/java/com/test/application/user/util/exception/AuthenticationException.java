package com.test.application.user.util.exception;
import org.springframework.http.HttpStatus;

import lombok.Getter;

/**
 * Clase cuando falla autenticación
 *
 * @author William Villaueva
 * @version 1.0.0
 */
@Getter
public class AuthenticationException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final HttpStatus status;

    public AuthenticationException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

}
