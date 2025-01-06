package com.springboot.jobsearch.controller;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.jobsearch.dto.JobRegister;
import com.springboot.jobsearch.entity.Job;
import com.springboot.jobsearch.entity.User;
import com.springboot.jobsearch.services.JobService;
import com.springboot.jobsearch.services.UserService;

@RestController
@RequestMapping("/jobs")
public class JobController {

	@Autowired
	JobService jobService;

	@PostMapping("/create/{recruiterId}")
	public ResponseEntity<Job> registerJob(@PathVariable int recruiterId, @RequestBody JobRegister jobRegister) {
		try {
			Job job = jobService.createJob(recruiterId, jobRegister);
			return ResponseEntity.status(201).body(job); // Return 201 Created status
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(400).body(null); // Return 400 Bad Request if invalid role or recruiter not
															// found
		} catch (Exception e) {
			return ResponseEntity.status(500).body(null); // Return 500 Internal Server Error in case of other issues
		}
	}

	@PostMapping("/{jobId}/apply/{userId}")
	public ResponseEntity<Job> applyJob(@PathVariable int jobId, @PathVariable int userId) {
		try {
			Job job = jobService.applyJob(userId, jobId);
			return ResponseEntity.ok(job);	// Return 200 OK with the updated job
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(400).body(null);	// Return 400 Bad Request for validation errors
		} catch (Exception e) {
			return ResponseEntity.status(500).body(null);	// Return 500 Internal Server Error for unexpected issues
		}
	}
	
	@DeleteMapping("/{jobId}/recruiter/{recruiterId}")
	public ResponseEntity<String> deleteJob( @PathVariable int jobId, @PathVariable int recruiterId) {
		try {
			jobService.deleteJob(jobId, recruiterId);
			return ResponseEntity.ok("Job deleted successfully");
		}
		catch (IllegalArgumentException e) {
			return ResponseEntity.status(403).body(e.getMessage());	// 403 Forbidden
		}
		catch (Exception e) {
			return ResponseEntity.status(500).body("An unexpected error occurred");
		}
	}
}
