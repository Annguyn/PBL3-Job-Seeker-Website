package com.backend.service.Impl;

import com.backend.entity.Application;
import com.backend.entity.Post;
import com.backend.entity.User;
import com.backend.repository.ApplicationRepository;
import com.backend.repository.PostRepository;
import com.backend.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, PostRepository postRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public List<Application> findByPostId(int id) {
        return applicationRepository.findByPostId(id) ;
    }

    @Override
    public void save(Application application) {
        applicationRepository.save(application);
    }

    @Override
    public Application findById(int id) {
        return applicationRepository.findById(id);
    }

    @Override
    public List<Application> findAllByUser(User user) {
        return applicationRepository.findAllByUser(user);
    }

    @Override
    public List<Application> findAllByPostCompany(int companyId) {
        return applicationRepository.findAllByPostCompany(companyId);
    }
}
