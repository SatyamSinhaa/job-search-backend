package com.springboot.jobsearch.dto;

import lombok.Data;

@Data
public class UserRegister {
	private String name;
    private String email;
    private String password;
    private String role;
}
