package br.com.flavioaugusto.gestao_vagas.service;

import br.com.flavioaugusto.gestao_vagas.controller.request.JobRequest;
import br.com.flavioaugusto.gestao_vagas.controller.response.JobResponse;
import br.com.flavioaugusto.gestao_vagas.entity.CompanyEntity;
import br.com.flavioaugusto.gestao_vagas.entity.JobEntity;
import br.com.flavioaugusto.gestao_vagas.exception.CompanyNotFoundException;
import br.com.flavioaugusto.gestao_vagas.mapper.JobMapper;
import br.com.flavioaugusto.gestao_vagas.repository.CompanyRepository;
import br.com.flavioaugusto.gestao_vagas.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobService {
    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;

    public JobResponse create(JobRequest jobRequest) {
        Optional<CompanyEntity> company = companyRepository.findById(jobRequest.company_id());
        JobEntity job = JobMapper.toJob(jobRequest);

        if (company.isPresent()) {
            job.setCompany(company.get());
            return JobMapper.toJobResponse(jobRepository.save(job));
        }

        throw new CompanyNotFoundException();
    }
}
