package br.com.flavioaugusto.gestao_vagas.controller.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record CompanyResponse(
        UUID id,
        String name,
        String username,
        String email,
        String description,
        String website
) {
}
