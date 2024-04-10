package com.backend.controller.home;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.backend.dto.UserDto;

import ch.qos.logback.core.model.Model;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@SessionAttributes("userdto")
public class HomeController {
    @ModelAttribute("userdto")
    public UserDto userDto(){
        return new UserDto();
    }
    @GetMapping("/home")
    public String showHomeForm(@ModelAttribute("userdto") UserDto userDto,Model model){

        return "index";
    }
}
