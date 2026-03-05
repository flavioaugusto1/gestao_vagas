package br.com.flavioaugusto.gestao_vagas.controller;

import br.com.flavioaugusto.gestao_vagas.controller.request.JobRequest;
import br.com.flavioaugusto.gestao_vagas.controller.response.JobResponse;
import br.com.flavioaugusto.gestao_vagas.service.JobService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company/job")
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;

    @PostMapping("/new")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<Object> save(@RequestBody JobRequest jobRequest, HttpServletRequest request) {
        try {
            String companyId = request.getAttribute("company_id").toString();
            JobResponse job = jobService.create(jobRequest, companyId);
            return ResponseEntity.status(HttpStatus.CREATED).body(job);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/job")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Object> findAllJobs(@RequestParam(required = false) String descriptionFilter) {
        try{
            List<JobResponse> jobs = jobService.listAllJobs(descriptionFilter);
            return ResponseEntity.status(HttpStatus.OK).body(jobs);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
