package com.springboot.jobsearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.jobsearch.entity.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
	
}
