package com.backend.service.Impl;

import com.backend.entity.Companies;
import com.backend.repository.CompaniesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompaniesService implements com.backend.service.CompaniesService {
    private final CompaniesRepository companiesRepository;

    public CompaniesService(CompaniesRepository companiesRepository) {
        this.companiesRepository = companiesRepository;
    }

    @Override
    public List<Companies> findAll() {
        return companiesRepository.findAll();
    }

    @Override
    public Companies findById(int id) {
        return companiesRepository.findById(id);
    }
}
