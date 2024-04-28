package com.backend.controller.navbar;

import com.backend.entity.Company;
import com.backend.entity.User;
import com.backend.service.CompanyService;
import com.backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class JobListingController {
    private final CompanyService companyService;
    private final UserService userService;

    @GetMapping("/joblisting")
    public String getJobListing(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.getUserbyEmail(username);
        Company company = user.getCompany() ;

        model.addAttribute("company", company);
        return "Company/job-listing";
    }
    @PostMapping("/joblisting")
    public String postJobListing() {
        return "Company/job-listing";
    }
}
