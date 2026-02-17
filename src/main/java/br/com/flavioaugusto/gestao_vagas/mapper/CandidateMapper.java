package br.com.flavioaugusto.gestao_vagas.mapper;

import br.com.flavioaugusto.gestao_vagas.controller.request.CandidateRequest;
import br.com.flavioaugusto.gestao_vagas.controller.response.CandidateResponse;
import br.com.flavioaugusto.gestao_vagas.entity.CandidateEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CandidateMapper {

    public static CandidateEntity toCandidateEntity(CandidateRequest candidateRequest) {
        return CandidateEntity.builder()
                .name(candidateRequest.name())
                .email(candidateRequest.email())
                .password(candidateRequest.password())
                .description(candidateRequest.description())
                .curriculum(candidateRequest.curriculum())
                .build();
    }

    public static CandidateResponse toCandidateResponse(CandidateEntity candidateEntity) {
        return CandidateResponse.builder()
                .id(candidateEntity.getId())
                .name(candidateEntity.getName())
                .email(candidateEntity.getEmail())
                .description(candidateEntity.getDescription())
                .curriculum(candidateEntity.getCurriculum())
                .build();
    }

}
