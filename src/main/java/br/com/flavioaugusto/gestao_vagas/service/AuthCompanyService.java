package br.com.flavioaugusto.gestao_vagas.service;

import br.com.flavioaugusto.gestao_vagas.controller.request.AuthCompanyRequest;
import br.com.flavioaugusto.gestao_vagas.controller.response.AuthTokenResponse;
import br.com.flavioaugusto.gestao_vagas.entity.CompanyEntity;
import br.com.flavioaugusto.gestao_vagas.exception.CompanyNotFoundException;
import br.com.flavioaugusto.gestao_vagas.mapper.AuthTokenMapper;
import br.com.flavioaugusto.gestao_vagas.repository.CompanyRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
public class AuthCompanyService {
    @Value("${security.token.secret}")
    private String secretKey;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthTokenResponse auth(AuthCompanyRequest authCompanyRequest) throws AuthenticationException {
        CompanyEntity company = companyRepository.findByUsername(authCompanyRequest.username())
                .orElseThrow(CompanyNotFoundException::new);

        boolean passwordMatches = passwordEncoder.matches(authCompanyRequest.password(), company.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiresIn = Instant.now().plus(Duration.ofHours(2));

        var token = JWT.create().withIssuer("javagas")
                .withExpiresAt(expiresIn)
                .withSubject(company.getId().toString())
                .withClaim("roles", List.of("COMPANY"))
                .sign(algorithm);

        return AuthTokenMapper.toAuthTokenResponse(token, expiresIn.toEpochMilli());
    }
}
