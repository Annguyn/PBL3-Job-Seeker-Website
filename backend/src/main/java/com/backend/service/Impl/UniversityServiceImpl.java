package com.backend.service.Impl;

import com.backend.entity.University;
import com.backend.repository.UniversityRepository;
import com.backend.service.UniversityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UniversityServiceImpl implements UniversityService {
    private final UniversityRepository universityRepository;

    public UniversityServiceImpl(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }

    @Override
    public University findById(int id) {
        return universityRepository.findUniversityById(id) ;
    }
}
