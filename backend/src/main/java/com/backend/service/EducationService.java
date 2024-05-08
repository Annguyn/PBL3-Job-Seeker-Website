package com.backend.service;

import com.backend.entity.Education;
import org.springframework.stereotype.Service;

@Service
public interface EducationService {
    void save(Education education) ;
}
