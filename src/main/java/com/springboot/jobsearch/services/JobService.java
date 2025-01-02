package com.springboot.jobsearch.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.jobsearch.dto.JobRegister;
import com.springboot.jobsearch.entity.Job;
import com.springboot.jobsearch.entity.Role;
import com.springboot.jobsearch.entity.User;
import com.springboot.jobsearch.repository.JobRepository;
import com.springboot.jobsearch.repository.UserRepository;

@Service
public class JobService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	private JobRepository jobRepository;

	public JobService(UserRepository userRepository, JobRepository jobRepository) {
		this.userRepository = userRepository;
		this.jobRepository = jobRepository;
	}

	public Job createJob(int recruiterId, JobRegister jobRegister) {
		// Check if the recruiter exists
		User recruiter = userRepository.findById(recruiterId)
				.orElseThrow(() -> new IllegalArgumentException("Recruiter not found"));
		System.out.println(recruiter);

		// Check if the user is a recruiter
		if (recruiter.getRole() != Role.RECRUITER) {
			throw new IllegalArgumentException("Only recruiters can post jobs");
		}
		Job job = new Job(jobRegister.getTitle(), jobRegister.getDescription(), jobRegister.getLocation(), recruiter);
		System.out.println(job);
		return jobRepository.save(job);
	}

	public Job applyJob(int userId, int jobId) {
		// Check if the user exists
		User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("user not found"));
		System.out.println(user);

		// Check if the user is an applicant
		if (user.getRole() != Role.APPLICANT) {
			System.out.println("recruiter");
			throw new IllegalArgumentException("only applicants can apply for jobs");
		}

		// Check if the job exists
		Job job = jobRepository.findById(jobId).orElseThrow(() -> new IllegalArgumentException("job not found"));

		// Add the user to the job's applicants list
		if (!job.getApplicants().contains(user)) {
			job.getApplicants().add(user);
			user.getJobsApplied().add(job);
			jobRepository.save(job);
			userRepository.save(user);
		} else {
			throw new IllegalArgumentException("User has already applied for this job");
		}
		return job;
	}

//	public Job applyJob(int userId, int jobId) {
//	    // Check if the user exists and has the correct role
//	    User user = userRepository.findById(userId)
//	            .orElseThrow(() -> new IllegalArgumentException("User not found"));
//
//	    if (user.getRole() != Role.APPLICANT) {
//	        throw new IllegalArgumentException("Only applicants can apply for jobs");
//	    }
//
//	    // Check if the job exists
//	    Job job = jobRepository.findById(jobId)
//	            .orElseThrow(() -> new IllegalArgumentException("Job not found"));
//
//	    // Check if the user has already applied for the job
//	    if (job.getApplicants().contains(user)) {
//	        throw new IllegalArgumentException("User has already applied for this job");
//	    }
//
//	    // Add the user to the job's applicants list and sync the bidirectional relationship
//	    job.getApplicants().add(user);
//	    user.getJobsApplied().add(job);
//
//	    // Save both sides to ensure consistency
//	    jobRepository.save(job);
//	    userRepository.save(user);
//
//	    return job;
//	}
}