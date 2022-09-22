package com.deliverar.admin.handlers;

import com.deliverar.admin.exceptions.OperatorNotFoundException;
import com.deliverar.admin.model.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class OperatorExceptionHandler {

    @ExceptionHandler(OperatorNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleOperatorNotFoundException(OperatorNotFoundException p) {
        ExceptionResponse ex = ExceptionResponse.builder()
                .message(p.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }
}
