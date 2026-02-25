package br.com.flavioaugusto.gestao_vagas.controller.request;

import br.com.flavioaugusto.gestao_vagas.entity.CompanyEntity;

import java.util.UUID;

public record JobRequest(
        String description,
        String benefits,
        String level,
        UUID company_id
) {
}
