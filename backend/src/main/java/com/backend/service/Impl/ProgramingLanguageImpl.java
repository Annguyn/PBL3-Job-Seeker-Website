package com.backend.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.entity.ProgramingLanguage;
import com.backend.repository.ProgramingLanguageRepository;
import com.backend.service.ProgrammingLanguageService;

@Service
public class ProgramingLanguageImpl implements ProgrammingLanguageService{

    ProgramingLanguageRepository programingLanguageRepository;
    @Override
    public List<ProgramingLanguage> getAllProgramingLanguages() {
        return programingLanguageRepository.findAll();
    }
    
}
