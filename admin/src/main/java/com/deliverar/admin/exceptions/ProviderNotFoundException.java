package com.deliverar.admin.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class ProviderNotFoundException extends RuntimeException{

    private String message;
}
