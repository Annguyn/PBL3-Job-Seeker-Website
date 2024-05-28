package com.backend.service.Impl;

import com.backend.entity.Application;
import com.backend.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.dto.UserDto;
import com.backend.entity.Company;
import com.backend.entity.User;
import com.backend.repository.CompanyRepository;
import com.backend.repository.UserRepository;
import com.backend.service.UserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public void save(UserDto userDto) {
        LocalDateTime Date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String creationDate = Date.format(formatter);
        User user = new User(userDto.getEmail(),
                userDto.getUserDisplayName(),
                userDto.getPassword(),
                userDto.getRole());
        userRepository.save(user);        
        if ("company".equals(userDto.getRole())) {
            Company company = new Company();

            company.setUser(user);
            user.setCompany(company);
            companyRepository.save(company);
        }
        
    }

    @Override
    public Boolean checkPasswordUser(String email, String password) {
        User user = userRepository.findUserByEmail(email);
        return user != null && user.getPassword().equals(password);
    }

    @Override
    public Boolean checkUserbyEmail(String email) {
        return userRepository.findUserByEmail(email) != null;
    }

    @Override
    public User getUserbyEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public java.util.List<User> getAllUsers() {
        return userRepository.getAllUser();
    }
    @Override
    public void save(User user) {
        userRepository.save(user);
    }
    @Override
    public User findById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<Application> upComingInterviews(User user) {
        LocalDateTime now = LocalDateTime.now();

        List<Application> allApplications = applicationRepository.findAllByUser(user);

        return allApplications.stream()
                .filter(application -> application.getInterviewStartTime() != null && application.getInterviewStartTime().isAfter(now) && application.getStatus().equals("Interview"))
                .collect(Collectors.toList());
    }
}
