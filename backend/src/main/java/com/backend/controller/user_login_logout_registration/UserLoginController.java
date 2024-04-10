package com.backend.controller.user_login_logout_registration;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.backend.dto.UserDto;
import com.backend.entity.User;
import com.backend.repository.UserRepository;
import com.backend.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
@SessionAttributes("userdto")
public class UserLoginController {
    private UserService userService;
    @ModelAttribute("userdto")
    public UserDto userDto() {
        return new UserDto();
    }

    @GetMapping("/login")
    public String GetLoginPage() {  
        return "login";
    }

    @PostMapping("/login")
    public String Login(@ModelAttribute("userdto") UserDto userDto, Model model){
        if(userService.checkUserbyEmail(userDto.getEmail())==false){
            return "redirect:/login?emailwrong";
        }
        User user = userService.getUserbyEmail(userDto.getEmail());
        if(user.getRole().equals("admin")){
            return  "redirect:/admin_home";
        }
        if(userService.checkPasswordUser(userDto.getEmail(),userDto.getPassword())){
            return "redirect:/home";
        }

        return "redirect:/login?passwordwrong";
    }

}
