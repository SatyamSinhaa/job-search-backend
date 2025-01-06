package com.springboot.jobsearch.dto;

import java.util.List;
import java.util.stream.Collectors;
import com.springboot.jobsearch.entity.Role;
import com.springboot.jobsearch.entity.User;
import lombok.Data;

@Data
public class UserDTO {
	private int id;
	private String name;
	private String email;
	private String role;
	private List<JobDTO> jobsApplied;  
    private List<JobDTO> jobsPosted; 

	// Constructor to populate the DTO from a User entity
	public UserDTO(User user) {
		 this.id = user.getId();
	        this.name = user.getName();
	        this.email = user.getEmail();
	        this.role = user.getRole().name();

	        // If the user is an applicant, populate jobsApplied
	        if (user.getRole() == Role.APPLICANT) {
	            this.jobsApplied = user.getJobsApplied().stream()
	                .map(job -> new JobDTO(job.getId(), job.getTitle(), job.getDescription(), job.getLocation()))
	                .collect(Collectors.toList());
	        } else {
	            this.jobsApplied = null;  // No jobs applied if the user is a recruiter
	        }

	        // If the user is a recruiter, populate jobsPosted
	        if (user.getRole() == Role.RECRUITER) {
	            this.jobsPosted = user.getJobsPosted().stream()
	                .map(job -> new JobDTO(job.getId(), job.getTitle(), job.getDescription(), job.getLocation()))
	                .collect(Collectors.toList());
	        } else {
	            this.jobsPosted = null;  // No jobs posted if the user is an applicant
	        }
	}

	public UserDTO() {
		super();
	}
}
