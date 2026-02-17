package br.com.flavioaugusto.gestao_vagas.controller;

import br.com.flavioaugusto.gestao_vagas.controller.request.CandidateRequest;
import br.com.flavioaugusto.gestao_vagas.controller.response.CandidateResponse;
import br.com.flavioaugusto.gestao_vagas.entity.CandidateEntity;
import br.com.flavioaugusto.gestao_vagas.mapper.CandidateMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @PostMapping("/")
    public ResponseEntity<CandidateResponse> create(@Valid @RequestBody CandidateRequest candidateRequest) {
        CandidateEntity candidate = CandidateMapper.toCandidateEntity(candidateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(CandidateMapper.toCandidateResponse(candidate));
    }
}
