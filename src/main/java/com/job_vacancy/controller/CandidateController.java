package com.job_vacancy.controller;

import com.job_vacancy.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/candidates")
@RequiredArgsConstructor
public class CandidateController {
    private final CandidateService candidateService;

    @PostMapping("/{id}/resume")
    public ResponseEntity<String> uploadResume(@PathVariable Long id, @RequestParam("file")MultipartFile file){
        String path = candidateService.saveResume(id,file);
        return ResponseEntity.ok("Resume saved at: "+path);
    }
}
