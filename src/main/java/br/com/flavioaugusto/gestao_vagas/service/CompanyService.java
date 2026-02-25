package br.com.flavioaugusto.gestao_vagas.service;

import br.com.flavioaugusto.gestao_vagas.controller.request.CompanyRequest;
import br.com.flavioaugusto.gestao_vagas.controller.response.CompanyResponse;
import br.com.flavioaugusto.gestao_vagas.entity.CompanyEntity;
import br.com.flavioaugusto.gestao_vagas.exception.CompanyAlreadyExistsException;
import br.com.flavioaugusto.gestao_vagas.mapper.CompanyMapper;
import br.com.flavioaugusto.gestao_vagas.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyResponse create(CompanyRequest companyRequest) {
        CompanyEntity company = CompanyMapper.toCompanyEntity(companyRequest);
        Optional<CompanyEntity> companyExists = findByEmailOrUsername(company.getEmail(), company.getUsername());

        if (companyExists.isPresent()) {
            throw new CompanyAlreadyExistsException();
        }

        return CompanyMapper.toCompanyResponse(companyRepository.save(company));
    }

    private Optional<CompanyEntity> findByEmailOrUsername(String email, String username) {
        return companyRepository.findByEmailOrUsername(email, username);
    }
}
