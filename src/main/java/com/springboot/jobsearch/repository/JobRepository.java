package com.springboot.jobsearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.jobsearch.entity.Job;

import jakarta.transaction.Transactional;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
	@Modifying
	   @Transactional
	   @Query("DELETE FROM Job j WHERE j.id = :jobId AND j.postedBy.id = :recruiterId")
	    int deleteJob(int jobId, int recruiterId);
}
