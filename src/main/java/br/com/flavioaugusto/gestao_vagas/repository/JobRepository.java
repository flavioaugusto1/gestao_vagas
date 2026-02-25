package br.com.flavioaugusto.gestao_vagas.repository;

import br.com.flavioaugusto.gestao_vagas.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {
}
