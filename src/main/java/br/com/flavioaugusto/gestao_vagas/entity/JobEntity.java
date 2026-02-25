package br.com.flavioaugusto.gestao_vagas.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "tb_job")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String description;
    private String benefits;
    private String level;

    @ManyToOne()
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
