package com.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.entity.ProgramingLanguage;

@Service
public interface ProgrammingLanguageService {
    public List<ProgramingLanguage> getAllProgramingLanguages();    
}