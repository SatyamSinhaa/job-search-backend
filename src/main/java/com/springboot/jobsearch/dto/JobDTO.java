package com.springboot.jobsearch.dto;

import java.util.List;

import com.springboot.jobsearch.entity.Job;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDTO {
	private int id;
	private String title;
	private String company;
	private String experience;
	private String salary;
	private String location;
	private String description;
	private List<String> tags;

	public JobDTO(Job updatedJob) {
		super();
		this.id = updatedJob.getId();
		this.title = updatedJob.getTitle();
		this.company = updatedJob.getCompany();
		this.experience = updatedJob.getExperience();
		this.salary = updatedJob.getSalary();
		this.location = updatedJob.getLocation();
		this.description = updatedJob.getDescription();
		this.tags = updatedJob.getTags();
	}	
    
    
}
