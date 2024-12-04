package com.bytmasoft.dss.controller;

import com.bytmasoft.common.exception.DSSErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UserControllerAdvice {

    private Logger logger = LoggerFactory.getLogger(UserControllerAdvice.class);


@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<DSSErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
    logger.error("An error occurred: {}", ex.getMessage(), ex);
    String path = request.getDescription(false); // URL and query string

    DSSErrorResponse errorResponse = customErrorResponse(HttpStatus.BAD_REQUEST.value(), "Access Denied",
            "Access of student service denied " + path);
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
}

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<DSSErrorResponse> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        logger.error("An error occurred: {}", ex.getMessage(), ex);
        String path = request.getDescription(false); // URL and query string

        DSSErrorResponse errorResponse = customErrorResponse(HttpStatus.FORBIDDEN.value(), "Access Denied",
                "Access of student service denied " + path);
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<DSSErrorResponse> handleExpiredJwtException(ExpiredJwtException ex, WebRequest request) {
        logger.error("An error occurred: {}", ex.getMessage(), ex);
        String path = request.getDescription(false); // URL and query string

        DSSErrorResponse errorResponse = customErrorResponse(HttpStatus.UNAUTHORIZED.value(), "Access Denied",
                "Access Denied token expired " + path);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }



    private DSSErrorResponse customErrorResponse(int status , String error, String message) {
        return  DSSErrorResponse.builder()
                .statusCode(status)
                .errorCode(error)
                .message(message)
                .build();
    }
}
