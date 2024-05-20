package com.backend.controller.admin;

import com.backend.entity.Application;
import com.backend.entity.User;
import com.backend.repository.UserRepository;
import com.backend.service.ApplicationService;
import com.backend.service.UniversityService;
import com.backend.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResumeController {
    private final UserService userService;

    public ResumeController( UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/resume")
    public String resume(@RequestParam("id") Integer id,  Model model) {
        User user = userService.findById(id) ;
        model.addAttribute("app", user);
        return "resume";
    }

}
