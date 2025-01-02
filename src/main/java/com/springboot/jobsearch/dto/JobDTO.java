package com.springboot.jobsearch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JobDTO {
    private int id;
    private String title;
    private String description;
    private String location;
}
