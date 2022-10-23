package com.deliverar.admin.handlers;

import com.deliverar.admin.exceptions.FranchiseNotFoundException;
import com.deliverar.admin.exceptions.ProviderNotFoundException;
import com.deliverar.admin.model.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class FranchiseExceptionHandler {

    @ExceptionHandler(FranchiseNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleFranchiseNotFoundException(FranchiseNotFoundException f){
        ExceptionResponse ex = ExceptionResponse.builder()
                .message(f.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }

}
