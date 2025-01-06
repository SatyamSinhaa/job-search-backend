package com.springboot.jobsearch.dto;

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
    private String description;
    private String location;

	public JobDTO(Job updatedJob) {
		super();
		this.id = updatedJob.getId();
		this.title = updatedJob.getTitle();
		this.description = updatedJob.getDescription();
		this.location = updatedJob.getLocation();
	}
    
    
}
