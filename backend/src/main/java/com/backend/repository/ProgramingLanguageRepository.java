package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.entity.ProgramingLanguage;

@Repository
public interface ProgramingLanguageRepository extends JpaRepository<ProgramingLanguage, Integer>{
    
}