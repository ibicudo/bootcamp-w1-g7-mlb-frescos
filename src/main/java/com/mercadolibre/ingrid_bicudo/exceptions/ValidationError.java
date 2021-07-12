package com.mercadolibre.ingrid_bicudo.exceptions;

import lombok.Getter;

@Getter
public class ValidationError {

    private final String field;
    private final String message;

    public ValidationError(String field, String message) {
        super();
        this.field = field;
        this.message = message;
    }

}