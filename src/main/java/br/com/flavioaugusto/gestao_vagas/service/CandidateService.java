package br.com.flavioaugusto.gestao_vagas.service;

import br.com.flavioaugusto.gestao_vagas.entity.CandidateEntity;
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

        if (candidateByEmail == null) {
            return candidateRepository.save(candidateEntity);
        }

        return null;
    }

    public CandidateEntity findByEmail(String email){
        Optional<CandidateEntity> candidate = candidateRepository.findByEmail(email);
        return candidate.orElse(null);
    }
}
