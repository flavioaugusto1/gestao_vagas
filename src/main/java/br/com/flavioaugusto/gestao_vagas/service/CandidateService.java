package br.com.flavioaugusto.gestao_vagas.service;

import br.com.flavioaugusto.gestao_vagas.controller.request.CandidateRequest;
import br.com.flavioaugusto.gestao_vagas.controller.response.CandidateResponse;
import br.com.flavioaugusto.gestao_vagas.entity.CandidateEntity;
import br.com.flavioaugusto.gestao_vagas.exception.UserAlreadyExistsException;
import br.com.flavioaugusto.gestao_vagas.mapper.CandidateMapper;
import br.com.flavioaugusto.gestao_vagas.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CandidateService {

    private final CandidateRepository candidateRepository;
    private final PasswordEncoder passwordEncoder;

    public CandidateResponse create(CandidateRequest candidateRequest) {
        Optional<CandidateEntity> candidateByEmail = findByEmail(candidateRequest.email());

        if (candidateByEmail.isPresent()) {
            throw new UserAlreadyExistsException();
        }

        var passwordEncoded = passwordEncoder.encode(candidateRequest.password());
        CandidateEntity candidate = CandidateMapper.toCandidateEntity(candidateRequest);
        candidate.setPassword(passwordEncoded);

        return CandidateMapper.toCandidateResponse(candidateRepository.save(candidate));
    }

    public CandidateResponse findById(UUID id) {
        CandidateEntity candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return CandidateMapper.toCandidateResponse(candidate);
    }

    public Optional<CandidateEntity> findByEmail(String email){
        return candidateRepository.findByEmail(email);
    }
}
