package com.job_vacancy.service;

import com.job_vacancy.model.Candidate;
import com.job_vacancy.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CandidateService {
    private final CandidateRepository candidateRepository;

    public String saveResume(Long candidateId, MultipartFile file){
        try{
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path path = Paths.get("/uploads/"+fileName);
            Files.createDirectories(path.getParent());
            Files.write(path,file.getBytes());

            Candidate candidate = candidateRepository.findById(candidateId)
                    .orElseThrow(() -> new RuntimeException("Candidate not found"));
            candidate.setResumePath(path.toString());
            candidateRepository.save(candidate);

            return path.toString();
        }
        catch(IOException e){
            throw new RuntimeException("Failed to store resume",e);
        }
    }
}
