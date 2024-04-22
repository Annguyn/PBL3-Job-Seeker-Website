package com.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.entity.Company;

@Service
public interface CompanyService {
      Company saveCompany(Company company);
    List<Company> getAllCompanies();
    Company getCompanyById(int id);
    Company updateCompany(Company company);
    void deleteCompany(int id);
}
