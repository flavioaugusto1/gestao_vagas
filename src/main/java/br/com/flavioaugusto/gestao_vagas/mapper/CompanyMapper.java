package br.com.flavioaugusto.gestao_vagas.mapper;

import br.com.flavioaugusto.gestao_vagas.controller.request.CompanyRequest;
import br.com.flavioaugusto.gestao_vagas.controller.response.CompanyResponse;
import br.com.flavioaugusto.gestao_vagas.entity.CompanyEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CompanyMapper {

    public static CompanyEntity toCompanyEntity(CompanyRequest companyRequest) {
        return CompanyEntity.builder()
                .name(companyRequest.name())
                .username(companyRequest.username())
                .email(companyRequest.email())
                .password(companyRequest.password())
                .description(companyRequest.description())
                .website(companyRequest.website())
                .build();
    }

    public static CompanyResponse toCompanyResponse(CompanyEntity companyEntity) {
        return CompanyResponse.builder()
                .id(companyEntity.getId())
                .name(companyEntity.getName())
                .username(companyEntity.getUsername())
                .email(companyEntity.getEmail())
                .description(companyEntity.getDescription())
                .website(companyEntity.getWebsite())
                .build();
    }

}
