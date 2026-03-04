package br.com.flavioaugusto.gestao_vagas.mapper;

import br.com.flavioaugusto.gestao_vagas.controller.response.AuthTokenResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AuthTokenMapper {

    public static AuthTokenResponse toAuthTokenResponse(String token, Long expiresIn){
        return AuthTokenResponse.builder()
                .access_token(token)
                .expires_in(expiresIn)
                .build();
    }
}
