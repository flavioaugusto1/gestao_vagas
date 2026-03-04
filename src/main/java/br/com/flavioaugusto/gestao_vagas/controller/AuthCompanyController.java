package br.com.flavioaugusto.gestao_vagas.controller;

import br.com.flavioaugusto.gestao_vagas.controller.request.AuthCompanyRequest;
import br.com.flavioaugusto.gestao_vagas.service.AuthCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class AuthCompanyController {

    private final AuthCompanyService authCompanyService;

    @PostMapping("/auth")
    public ResponseEntity<Object> auth(@RequestBody AuthCompanyRequest authCompanyRequest) throws AuthenticationException {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(authCompanyService.auth(authCompanyRequest));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}
