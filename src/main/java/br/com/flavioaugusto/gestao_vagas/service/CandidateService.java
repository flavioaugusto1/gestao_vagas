package br.com.flavioaugusto.gestao_vagas.service;

import br.com.flavioaugusto.gestao_vagas.controller.request.CandidateRequest;
import br.com.flavioaugusto.gestao_vagas.controller.response.CandidateResponse;
import br.com.flavioaugusto.gestao_vagas.entity.CandidateEntity;
import br.com.flavioaugusto.gestao_vagas.exception.UserAlreadyExistsException;
import br.com.flavioaugusto.gestao_vagas.mapper.CandidateMapper;
import br.com.flavioaugusto.gestao_vagas.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CandidateService {

    private final CandidateRepository candidateRepository;

    public CandidateResponse create(CandidateRequest candidateRequest) {
        Optional<CandidateEntity> candidateByEmail = findByEmail(candidateRequest.email());

        if (candidateByEmail.isPresent()) {
            throw new UserAlreadyExistsException();
        }

        CandidateEntity candidate = CandidateMapper.toCandidateEntity(candidateRequest);

        return CandidateMapper.toCandidateResponse(candidateRepository.save(candidate));
    }

    public Optional<CandidateEntity> findByEmail(String email){
        return candidateRepository.findByEmail(email);
    }
}
