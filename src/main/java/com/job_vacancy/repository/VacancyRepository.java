package com.job_vacancy.repository;

import com.job_vacancy.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacancyRepository extends JpaRepository<Vacancy,Long> {
}
