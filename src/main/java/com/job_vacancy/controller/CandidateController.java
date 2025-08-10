package com.job_vacancy.controller;

import com.job_vacancy.model.Candidate;
import com.job_vacancy.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/candidates")
@RequiredArgsConstructor
public class CandidateController {
    private final CandidateService candidateService;

    @PostMapping
    public ResponseEntity<Candidate> createCandidate(@RequestBody Candidate candidate){
        Candidate savedCandidate = candidateService.createCandidate(candidate);
        return ResponseEntity.ok(savedCandidate);
    }

    @PostMapping(value = "/{id}/resume", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadResume(@PathVariable Long id, @RequestPart("file")MultipartFile file){
        String path = candidateService.saveResume(id,file);
        return ResponseEntity.ok("Resume saved at: "+path);
    }
}
