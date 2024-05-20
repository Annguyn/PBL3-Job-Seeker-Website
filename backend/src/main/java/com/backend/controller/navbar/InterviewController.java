package com.backend.controller.navbar;

import com.backend.entity.Application;
import com.backend.entity.Company;
import com.backend.entity.User;
import com.backend.repository.ApplicationRepository;
import com.backend.service.ApplicationService;
import com.backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class InterviewController {
    private final UserService userService;
    private final ApplicationService applicationService;
    private final ApplicationRepository applicationRepository;

    @GetMapping("/interview")
    public String showInterviewForm(Model model , @RequestParam("id") Integer id ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.getUserbyEmail(username);
        Company company = user.getCompany() ;
        if (id == null) {
            return "error";
        }
        List<Application> applications = applicationRepository.findAll();
        List<Application> interviewApplications = applications.stream()
                .filter(application -> "Interview".equals(application.getStatus()))
                .collect(Collectors.toList());

        model.addAttribute("interviewApplications", interviewApplications);
        Application application = applicationService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("applyForm" , application);
        model.addAttribute("company", company);
        return "Company/applicant-interview";
    }
}
