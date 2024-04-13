package com.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private int id;
    private int company_id;
    private int maxSalary;
    private int minSalary;
    private String phone_number;
    private String email;
    private String content;
    private String images;
    private int experience;
    private int location_id;
    private int level_id;
    private int languages_id;
}
