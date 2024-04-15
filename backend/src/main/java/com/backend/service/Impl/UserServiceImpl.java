package com.backend.service.Impl;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.dto.UserDto;
import com.backend.entity.User;
import com.backend.repository.UserRepository;
import com.backend.service.UserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public void save(UserDto userDto) {
        LocalDateTime Date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String creationDate = Date.format(formatter);
        User user = new User(userDto.getEmail(),
                userDto.getUserDisplayName(),
                userDto.getPassword(),
                userDto.getRole()
        );
       
        userRepository.save(user);
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
}
