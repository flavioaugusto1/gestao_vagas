package br.com.flavioaugusto.gestao_vagas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "tb_candidate")
public class CandidateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String username;

    @NotNull
    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
    private String description;
    private String curriculum;
    @CreationTimestamp
    private LocalDateTime created_at;
}
