package com.springboot.jobsearch.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JobRegister {
	private String title;
	private String company;
	private String experience;
	private String salary;
	private String location;
	private String description;
	private List<String> tags;
}
