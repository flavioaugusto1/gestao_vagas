package br.com.flavioaugusto.gestao_vagas.service;

import br.com.flavioaugusto.gestao_vagas.entity.CandidateEntity;
import br.com.flavioaugusto.gestao_vagas.exception.UserAlreadyExistsException;
import br.com.flavioaugusto.gestao_vagas.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CandidateService {

    private final CandidateRepository candidateRepository;

    public CandidateEntity create(CandidateEntity candidateEntity) {
        CandidateEntity candidateByEmail = findByEmail(candidateEntity.getEmail());

        if (candidateByEmail != null) {
            throw new UserAlreadyExistsException();

        }

        return candidateRepository.save(candidateEntity);
    }

    public CandidateEntity findByEmail(String email){
        return candidateRepository.findByEmail(email)
                .orElse(null);
    }
}
