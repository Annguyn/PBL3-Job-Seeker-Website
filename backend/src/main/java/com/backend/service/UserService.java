package com.backend.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.dto.UserDto;
import com.backend.entity.User;

@Service
public interface UserService {
    void save(UserDto userDto);
    Boolean checkPasswordUser(String email,String password);
    Boolean checkUserbyEmail(String email);
    User getUserbyEmail(String email);
    List<User> getAllUsers();
    void save(User user);
    User findById(int id);
}
