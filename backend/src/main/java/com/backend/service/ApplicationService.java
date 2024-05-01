package com.backend.service;

import com.backend.entity.Application;
import com.backend.entity.User;
import com.backend.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ApplicationService {
    List<Application> findByPostId(int id);
    Application findById(int id) ;
    List<Application> findAllByUser(User user);
    List<Application> findAllByPostCompany(int companyId);
    void save(Application application);
}
