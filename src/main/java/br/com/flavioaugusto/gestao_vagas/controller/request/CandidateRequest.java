package br.com.flavioaugusto.gestao_vagas.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record CandidateRequest(
        String name,

        @Pattern(regexp = "\\S+", message = "O campo [username] não pode conter espaços.")
        String username,

        @Email(message = "Precisa de um e-mail válido")
        String email,

        @Length(min = 6, max = 18, message = "A senha deve conter entre 6 e 18 caracteres.")
        String password,

        String description,

        String curriculum
) {
}
