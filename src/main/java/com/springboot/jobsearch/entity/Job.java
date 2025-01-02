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
	private String description;
	private String location;

	@ManyToOne
	@JoinColumn(name = "posted_by_id", nullable = false)
	private User postedBy; // Recruiter who posted the job

	@ManyToMany(mappedBy = "jobsApplied")
//	@JsonIgnore
	private List<User> applicants; // List of applicants for this job

	public Job(String title, String description, String location, User postedBy) {
		this.title = title;
		this.description = description;
		this.location = location;
		this.postedBy = postedBy;
	}
}
