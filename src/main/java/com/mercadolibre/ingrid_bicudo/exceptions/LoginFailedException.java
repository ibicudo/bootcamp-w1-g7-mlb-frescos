package com.mercadolibre.ingrid_bicudo.exceptions;

public class LoginFailedException extends ApiException{
    public LoginFailedException(String detail){
        super("login_failed", detail, 402 );
    }
}
