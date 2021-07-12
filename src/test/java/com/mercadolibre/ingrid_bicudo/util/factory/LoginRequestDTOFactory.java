package com.mercadolibre.ingrid_bicudo.util.factory;

import com.mercadolibre.ingrid_bicudo.dtos.LoginRequestDTO;

public class LoginRequestDTOFactory {
    static public LoginRequestDTO getLoginMock(){
        return new LoginRequestDTO("ton", "123456");
    }
}
