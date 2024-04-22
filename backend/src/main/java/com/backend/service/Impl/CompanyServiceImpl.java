package com.backend.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.entity.Company;
import com.backend.repository.CompanyRepository;
import com.backend.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {
      @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompanyById(int id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public Company updateCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public void deleteCompany(int id) {
        companyRepository.deleteById(id);
    }
}
