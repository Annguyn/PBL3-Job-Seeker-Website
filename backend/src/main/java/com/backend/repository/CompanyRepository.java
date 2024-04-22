package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.backend.entity.Company;

@Repository
@EnableJpaRepositories
public interface CompanyRepository extends JpaRepository<Company, Integer>{
    
}
