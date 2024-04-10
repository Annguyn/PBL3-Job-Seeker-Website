package com.backend.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.backend.dto.UserDto;
import com.backend.entity.User;
import com.backend.repository.UserRepository;

@Controller
@SessionAttributes("userdto")
public class AdminUserController {

    // @Autowired
    // private UserRepository userRepository;

    @ModelAttribute("userdto")
    public UserDto userDto() {
        return new UserDto();
    }

    // @GetMapping("/Users")
    // public String showUserControl(@ModelAttribute("userdto") UserDto userDto, Model model) {
    //     List<User> users = userRepository.getAllUser();
    //     model.addAttribute("users", users);
    //     return "table_user";
    // }
}
