package com.job_vacancy.controller;

import com.job_vacancy.model.Vacancy;
import com.job_vacancy.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vacancies")
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyService vacancyService;

    @PostMapping
    public ResponseEntity<Vacancy> createVacancy(@RequestBody Vacancy vacancy){
        return ResponseEntity.ok(vacancyService.createVacancy(vacancy));
    }

    @GetMapping
    public ResponseEntity<List<Vacancy>> getAllVacancy(){
        return ResponseEntity.ok(vacancyService.getAllVacancy());
    }
}
