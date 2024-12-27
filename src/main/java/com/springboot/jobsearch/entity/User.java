package com.springboot.jobsearch.entity;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @Column(unique = true, nullable = false)
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role; // APPLICANT or RECRUITER

    @OneToMany(mappedBy = "postedBy", cascade = CascadeType.ALL)
    private List<Job> jobsPosted; // Jobs posted by the recruiter

    @ManyToMany
    @JoinTable(
        name = "job_applications",
        joinColumns = @JoinColumn(name = "applicant_id"),
        inverseJoinColumns = @JoinColumn(name = "job_id")
    )
    private List<Job> jobsApplied; // Jobs the applicant has applied for
}

enum Role {
    APPLICANT, RECRUITER
}


