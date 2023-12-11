package com.test.application.user.util.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

/**
 * Clase para validación de data
 *
 * @author William Villaueva
 * @version 1.0.0
 */
@Getter
public class DataValidationException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final HttpStatus status;

    public DataValidationException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

}
