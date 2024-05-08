package com.backend.service;

import com.backend.entity.University;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UniversityService {
    List<University> getAllUniversities();
    University findById(int id);
}
