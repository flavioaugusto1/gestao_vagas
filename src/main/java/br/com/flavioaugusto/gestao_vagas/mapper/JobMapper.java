package br.com.flavioaugusto.gestao_vagas.mapper;

import br.com.flavioaugusto.gestao_vagas.controller.request.JobRequest;
import br.com.flavioaugusto.gestao_vagas.controller.response.JobResponse;
import br.com.flavioaugusto.gestao_vagas.entity.JobEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JobMapper {

    public static JobEntity toJob(JobRequest jobRequest){
        return JobEntity.builder()
                .description(jobRequest.description())
                .benefits(jobRequest.benefits())
                .level(jobRequest.level())
                .build();
    }

    public static JobResponse toJobResponse(JobEntity jobEntity){
        return JobResponse.builder()
                .id(jobEntity.getId())
                .description(jobEntity.getDescription())
                .benefits(jobEntity.getBenefits())
                .level(jobEntity.getLevel())
                .company(CompanyMapper.toCompanyResponse(jobEntity.getCompany()))
                .build();
    }

}
