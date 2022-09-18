package com.deliverar.admin.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter @Builder
public class ExceptionResponse {

    private String message;
    private LocalDateTime timestamp;
}
