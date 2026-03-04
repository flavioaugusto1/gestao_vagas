package br.com.flavioaugusto.gestao_vagas.service;

import br.com.flavioaugusto.gestao_vagas.controller.request.AuthCandidateRequest;
import br.com.flavioaugusto.gestao_vagas.controller.response.AuthTokenResponse;
import br.com.flavioaugusto.gestao_vagas.entity.CandidateEntity;
import br.com.flavioaugusto.gestao_vagas.exception.InvalidCredentialsException;
import br.com.flavioaugusto.gestao_vagas.mapper.AuthTokenMapper;
import br.com.flavioaugusto.gestao_vagas.repository.CandidateRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
public class AuthCandidateService {
    @Value("${security.token.secret}")
    private String secretKey;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthTokenResponse auth(AuthCandidateRequest authCandidateRequest) {
        CandidateEntity candidate = candidateRepository.findByUsername(authCandidateRequest.username())
                .orElseThrow(InvalidCredentialsException::new);

        var passwordMatch = passwordEncoder.matches(authCandidateRequest.password(), candidate.getPassword());

        if (!passwordMatch) {
            throw new InvalidCredentialsException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);


        var expiresIn = Instant.now().plus(Duration.ofHours(2));
        var token =  JWT.create()
                .withIssuer("javagas")
                .withClaim("candidate_id", candidate.getId().toString())
                .withClaim("roles", List.of("CANDIDATE"))
                .withSubject(candidate.getId().toString())
                .withExpiresAt(expiresIn)
                .sign(algorithm);

        return AuthTokenMapper.toAuthTokenResponse(token, expiresIn.toEpochMilli());

    }

}
