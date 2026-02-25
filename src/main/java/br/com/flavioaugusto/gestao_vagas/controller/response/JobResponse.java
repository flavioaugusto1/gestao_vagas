package br.com.flavioaugusto.gestao_vagas.controller.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record JobResponse(
        UUID id,
        String description,
        String benefits,
        String level,
        CompanyResponse company
) {
}
