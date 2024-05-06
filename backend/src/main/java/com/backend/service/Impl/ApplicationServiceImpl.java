package com.backend.service.Impl;

import com.backend.entity.Application;
import com.backend.entity.Post;
import com.backend.entity.User;
import com.backend.repository.ApplicationRepository;
import com.backend.repository.PostRepository;
import com.backend.service.ApplicationService;
import com.backend.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final MailService mailService;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, PostRepository postRepository, MailService mailService) {
        this.applicationRepository = applicationRepository;
        this.mailService = mailService;
    }

    @Override
    public List<Application> findByPostId(int id) {
        return applicationRepository.findByPostId(id) ;
    }

    @Override
    public void save(Application application) {
        applicationRepository.save(application);

        String toCompany = null;
        try {
            toCompany = application.getPost().getCompany().getUser().getEmail();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String subjectToCompany = "New Application Received";
        String textToCompany = "A new application has been received from " + application.getUser().getEmail();
        mailService.sendSimpleMessage(toCompany, subjectToCompany, textToCompany);
        String toUser = application.getUser().getEmail() ;
        String subjectToUser = "Application Sent";
        String textToUser = "You have successfully applied to " + application.getPost().getTitle();
        mailService.sendSimpleMessage(toUser, subjectToUser, textToUser);

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
