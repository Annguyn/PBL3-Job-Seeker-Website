package com.backend.service;

import com.backend.entity.Companies;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompaniesService {
    List<Companies> findAll();
    Companies findById(int id);
}
