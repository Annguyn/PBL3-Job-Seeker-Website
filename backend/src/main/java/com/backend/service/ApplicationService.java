package com.backend.service;

import com.backend.entity.Application;
import com.backend.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface ApplicationService {
    void save(Application application);
}
