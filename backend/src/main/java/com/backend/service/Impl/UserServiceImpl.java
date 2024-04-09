package com.backend.service.Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.dto.UserDto;
import com.backend.entity.Profile;
import com.backend.entity.User;
import com.backend.repository.ProfileRepository;
import com.backend.repository.UserRepository;
import com.backend.service.UserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public void save(UserDto userDto) {
        LocalDateTime Date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String creationDate = Date.format(formatter);
        User user = new User(userDto.getEmail(),
                userDto.getUserDisplayName(),
                // ".",
                // 0,
                // 0,
                userDto.getPassword(),
                // creationDate,
                "ROLE_USER"
        );
        Profile profile=new Profile(
                "First Name",
                "Last Name",
                creationDate,
                "null",
                "Gender",
                "Adress",
                user
        );
        userRepository.save(user);
        profileRepository.save(profile);
    }

    @Override
    public Boolean checkPasswordUser(String email, String password) {
        User user = userRepository.findUserByEmail(email);
        if (user.getPassword().equals(password)) return true;
        return false;
    }

    @Override
    public Boolean checkUserbyEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        if(user==null) return false;
        return true;
    }

    @Override
    public User getUserbyEmail(String email) {
        return userRepository.getUserByEmail(email);
    }
}
