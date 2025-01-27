package com.springboot.jobsearch.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String company;
	private String experience;
	private String salary;
	private String location;
	private String description;
	private List<String> tags;
	

	@ManyToOne
	@JoinColumn(name = "posted_by_id", nullable = false)
	private User postedBy; // Recruiter who posted the job

	@ManyToMany(mappedBy = "jobsApplied")
	@JsonIgnore
	private List<User> applicants; // List of applicants for this job

	public Job(String title, String description, String location, User postedBy) {
		this.title = title;
		this.description = description;
		this.location = location;
		this.postedBy = postedBy;
	}

	public Job(String title, String company, String experience, String salary, String location, String description,
			List<String> tags, User postedBy) {
		super();
		this.title = title;
		this.company = company;
		this.experience = experience;
		this.salary = salary;
		this.location = location;
		this.description = description;
		this.tags = tags;
		this.postedBy = postedBy;
	}
	
	@Override
	public String toString() {
	    return "Job{" +
	            "id=" + id +
	            ", title='" + title + '\'' +
	            ", company='" + company + '\'' +
	            ", experience='" + experience + '\'' +
	            ", salary='" + salary + '\'' +
	            ", location='" + location + '\'' +
	            ", description='" + description + '\'' +
	            ", tags=" + tags +
	            ", postedById=" + (postedBy != null ? postedBy.getId() : null) + // Show only the postedBy ID to prevent recursion
	            '}';
	}

}
