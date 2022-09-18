package com.deliverar.admin.handlers;

import com.deliverar.admin.exceptions.ProviderNotFoundException;
import com.deliverar.admin.model.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ProviderExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception e){
        ExceptionResponse ex = ExceptionResponse.builder()
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProviderNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleProviderNotFoundException(ProviderNotFoundException p){
        ExceptionResponse ex = ExceptionResponse.builder()
                .message(p.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }
}
