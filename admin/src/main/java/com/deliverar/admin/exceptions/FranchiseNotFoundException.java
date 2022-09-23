package com.deliverar.admin.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FranchiseNotFoundException extends RuntimeException {

    private String message;
}
