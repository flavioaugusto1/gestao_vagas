package br.com.flavioaugusto.gestao_vagas.controller;

import br.com.flavioaugusto.gestao_vagas.controller.request.CandidateRequest;
import br.com.flavioaugusto.gestao_vagas.controller.response.CandidateResponse;
import br.com.flavioaugusto.gestao_vagas.entity.CandidateEntity;
import br.com.flavioaugusto.gestao_vagas.mapper.CandidateMapper;
import br.com.flavioaugusto.gestao_vagas.service.CandidateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidate")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;

    @PostMapping("/")
    public ResponseEntity<CandidateResponse> create(@Valid @RequestBody CandidateRequest candidateRequest) {
       CandidateEntity candidate = CandidateMapper.toCandidateEntity(candidateRequest);
       CandidateEntity candidateSaved = candidateService.create(candidate);

       if(candidateSaved != null){
           return ResponseEntity.status(HttpStatus.CREATED).body(CandidateMapper.toCandidateResponse(candidateSaved));
       }

       return ResponseEntity.status(HttpStatus.CONFLICT).build();

    }
}
