package com.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.backend.entity.ProgramingLanguage;


@Repository
@EnableJpaRepositories
public interface ProgramingLanguageRepository extends JpaRepository<ProgramingLanguage, Integer>{
    List<ProgramingLanguage> findAll(); 
}