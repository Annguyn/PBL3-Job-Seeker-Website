package com.backend.service.Impl;

import com.backend.entity.Education;
import com.backend.repository.EducationRepository;
import com.backend.service.EducationService;
import org.springframework.stereotype.Service;

@Service
public class EducationServiceImpl  implements EducationService {
    private final EducationRepository educationRepository;

    public EducationServiceImpl(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    @Override
    public void save(Education education) {
        educationRepository.save(education);
    }

    @Override
    public void delete(int id) {
        educationRepository.deleteById(id);
    }

}
