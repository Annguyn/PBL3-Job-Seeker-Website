package com.backend.service.Impl;

import com.backend.entity.Application;
import com.backend.repository.ApplicationRepository;
import com.backend.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public void save(Application application) {
        applicationRepository.save(application);
    }
}
