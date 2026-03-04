package br.com.flavioaugusto.gestao_vagas.controller.response;

import lombok.Builder;

@Builder
public record AuthTokenResponse(String access_token, Long expires_in) {
}
