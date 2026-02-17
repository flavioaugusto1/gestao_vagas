package br.com.flavioaugusto.gestao_vagas.repository;

import br.com.flavioaugusto.gestao_vagas.entity.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {
    Optional<CandidateEntity> findByEmail(String email);
}
