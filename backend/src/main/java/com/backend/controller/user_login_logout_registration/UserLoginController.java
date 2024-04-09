package com.backend.controller.user_login_logout_registration;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.backend.dto.UserDto;
import com.backend.entity.User;
import com.backend.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@AllArgsConstructor
@SessionAttributes("userdto")
public class UserLoginController {
    private UserService userService;
    @ModelAttribute("userdto")
    public UserDto userDto(){
        return new UserDto();
    }
    @GetMapping("/login")
    public String GetLoginPage() {
        return "login";
    }
    
}
