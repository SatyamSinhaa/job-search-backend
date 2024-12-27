package com.springboot.jobsearch.entity;

import java.time.LocalDate;
import java.util.List;
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
    private LocalDate postedDate;

    @ManyToOne
    @JoinColumn(name = "posted_by_id", nullable = false)
    private User postedBy; // Recruiter who posted the job

    @ManyToMany(mappedBy = "jobsApplied")
    private List<User> applicants; // List of applicants for this job
}
