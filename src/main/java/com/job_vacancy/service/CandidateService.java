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

    public Candidate createCandidate(Candidate candidate){
        return candidateRepository.save(candidate);
    }

    public String saveResume(Long candidateId, MultipartFile file) {
        try {
            // Generate unique file name
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

            // Save inside project/uploads/
            Path uploadDir = Paths.get(System.getProperty("user.dir"), "uploads");
            Files.createDirectories(uploadDir);

            // Complete path
            Path filePath = uploadDir.resolve(fileName);

            // Write file
            Files.write(filePath, file.getBytes());

            // Update candidate record
            Candidate candidate = candidateRepository.findById(candidateId)
                    .orElseThrow(() -> new RuntimeException("Candidate not found"));
            candidate.setResumePath(filePath.toString());
            candidateRepository.save(candidate);

            return filePath.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to store resume", e);
        }
    }
}
