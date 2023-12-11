package com.test.application.config;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.test.application.dto.ErrorResponse;
import com.test.application.user.util.exception.DataValidationException;
import com.test.application.user.util.exception.AuthenticationException;

/**
 * Clase para personalizar los mensaje de respuesta
 *
 * @author William Villanueva
 * @version 1.0.0
 */
@ControllerAdvice
public class ApiException {

    @ExceptionHandler(DataValidationException.class)
    public ResponseEntity<ErrorResponse> handleDataValidationException(
            DataValidationException exception) {
        return new ResponseEntity<ErrorResponse>(generateErrorResponse(exception.getMessage()),
                exception.getStatus());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(
            AuthenticationException exception) {
        return new ResponseEntity<ErrorResponse>(generateErrorResponse(exception.getMessage()),
                exception.getStatus());
    }

    private ErrorResponse generateErrorResponse(String message) {
        return ErrorResponse.builder().message(message).build();
    }

}
