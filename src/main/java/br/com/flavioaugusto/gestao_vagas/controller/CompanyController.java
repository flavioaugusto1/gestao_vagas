package br.com.flavioaugusto.gestao_vagas.controller;

import br.com.flavioaugusto.gestao_vagas.controller.request.CompanyRequest;
import br.com.flavioaugusto.gestao_vagas.controller.response.CompanyResponse;
import br.com.flavioaugusto.gestao_vagas.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CompanyRequest companyRequest) {
        try{
            CompanyResponse company = companyService.create(companyRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(company);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
