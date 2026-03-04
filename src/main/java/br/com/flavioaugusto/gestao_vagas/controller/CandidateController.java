package br.com.flavioaugusto.gestao_vagas.controller;

import br.com.flavioaugusto.gestao_vagas.controller.request.CandidateRequest;
import br.com.flavioaugusto.gestao_vagas.controller.response.CandidateResponse;
import br.com.flavioaugusto.gestao_vagas.service.CandidateService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/candidate")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;

    @PostMapping("/new")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateRequest candidateRequest) {
        try {
            CandidateResponse candidate = candidateService.create(candidateRequest);

            return ResponseEntity.status(HttpStatus.CREATED).body(candidate);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Object> findById(HttpServletRequest request) {
        try{
            var id = UUID.fromString(request.getAttribute("candidate_id").toString());
            var candidate = candidateService.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(candidate);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
