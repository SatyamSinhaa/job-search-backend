# Job Search Application (Backend)

This is the backend for a Job Search Application developed using **Spring Boot**. The application provides functionalities for job listings, user management (applicants and recruiters), and job applications. It allows recruiters to post, update, and delete job listings, and applicants can apply for available jobs.

## Technologies Used

- **Spring Boot** - For building the backend API.
- **Spring Data JPA** - For interacting with the database.
- **MySQL** - For storing data about users, jobs, and applications.
- **Spring Security** (Optional for authentication and authorization)
- **Postman** - For testing API endpoints.

## Features

- **Job Listings**: Recruiters can post new jobs, update job details, and delete their posted jobs.
- **Job Applications**: Applicants can view available job listings and apply for them.
- **User Management**: Both applicants and recruiters can register, login, and manage their profiles.

## Endpoints

### User Endpoints

- **POST /users/register**: Register a new user (either applicant or recruiter).
- **POST /users/login/{email}/{password}**: Login for users (returns user details if credentials are valid).
- **GET /users/{userId}**: Get user details by ID.
- **PUT /users/{userId}**: Update user details by ID.

### Job Endpoints

- **GET /jobs**: Retrieve all jobs.
- **POST /jobs**: Create a new job (only accessible by recruiters).
- **GET /jobs/{jobId}**: Retrieve job details by ID.
- **PUT /jobs/{jobId}**: Update job details (only accessible by the recruiter who posted the job).
- **DELETE /jobs/{jobId}**: Delete a job posting (only accessible by the recruiter who posted the job).

### Job Application Endpoints

- **POST /applications/{jobId}/{userId}**: Apply for a job.
- **GET /applications/{userId}**: Get a list of jobs the user has applied to.

## Database Schema

The application uses MySQL to store data. Below is a high-level view of the database schema:

- **User** table: Stores information about users (applicants and recruiters).
- **Job** table: Stores job listings posted by recruiters.
- **Job_Application** table: Stores the relationship between applicants and the jobs they've applied to.
