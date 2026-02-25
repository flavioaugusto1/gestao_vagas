package br.com.flavioaugusto.gestao_vagas.repository;

import br.com.flavioaugusto.gestao_vagas.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {
    Optional<CompanyEntity> findByEmailOrUsername(String email, String username);
}
