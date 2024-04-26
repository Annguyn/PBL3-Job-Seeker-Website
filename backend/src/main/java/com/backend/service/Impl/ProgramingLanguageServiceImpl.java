package com.backend.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.entity.ProgramingLanguage;
import com.backend.repository.ProgramingLanguageRepository;
import com.backend.service.ProgrammingLanguageService;

@Service
public class ProgramingLanguageServiceImpl implements ProgrammingLanguageService{
    @Autowired
    ProgramingLanguageRepository programingLanguageRepository;
    @Override
    public List<ProgramingLanguage> getAllProgramingLanguages() {
        return programingLanguageRepository.findAll();
    }
    @Override
    public List<ProgramingLanguage> getProgramingLanguageByIds(List<Integer> ids) {
        return programingLanguageRepository.findByIdsList(ids);
    }
    
}
