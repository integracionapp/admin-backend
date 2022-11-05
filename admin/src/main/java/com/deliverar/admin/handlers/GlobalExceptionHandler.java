package com.deliverar.admin.handlers;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.deliverar.admin.exceptions.OperatorNotFoundException;
import com.deliverar.admin.exceptions.UsernameAlreadyExistException;
import com.deliverar.admin.model.dto.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleDefaultException(MethodArgumentNotValidException e){
        ExceptionResponse ex = ExceptionResponse.builder()
                .message(e.getFieldError().getDefaultMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleException(MethodArgumentNotValidException e){
        ExceptionResponse ex = ExceptionResponse.builder()
                .message(e.getFieldError().getDefaultMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UsernameAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> usernameAlreadyExistHandler(UsernameAlreadyExistException ex){
        return new ResponseEntity<>(ExceptionResponse.builder()
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build(), HttpStatus.OK);
    }

    @ExceptionHandler(OperatorNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleOperatorNotFoundException(OperatorNotFoundException p) {
        ExceptionResponse ex = ExceptionResponse.builder()
                .message(p.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }
}
