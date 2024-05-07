package com.backend.controller.admin;

import com.backend.entity.Application;
import com.backend.service.ApplicationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResumeController {
    private final ApplicationService applicationService;

    public ResumeController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/resume")
    public String resume(@RequestParam("id") Integer id,  Model model) {
        Application application = applicationService.findById(id);
        model.addAttribute("app", application);
        return "resume";
    }

}
