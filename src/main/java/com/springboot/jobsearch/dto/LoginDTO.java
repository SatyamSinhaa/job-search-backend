package com.springboot.jobsearch.dto;

import lombok.Data;

public class LoginDTO {
	private int userId;
    private String name;
    private String email;
    private String role;
    
	public LoginDTO(int userId, String name, String email, String role) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.role = role;
	}
}
