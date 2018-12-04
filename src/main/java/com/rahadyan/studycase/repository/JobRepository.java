package com.rahadyan.studycase.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rahadyan.studycase.model.Job;

public interface JobRepository extends JpaRepository<Job, Integer> {
}
