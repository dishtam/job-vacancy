package com.job_vacancy.service;

import com.job_vacancy.model.Vacancy;
import com.job_vacancy.repository.VacancyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyService {
    private final VacancyRepository vacancyRepository;

    public Vacancy createVacancy(Vacancy vacancy){
        return vacancyRepository.save(vacancy);
    }

    public List<Vacancy> getAllVacancy(){
        return vacancyRepository.findAll();
    }
}
