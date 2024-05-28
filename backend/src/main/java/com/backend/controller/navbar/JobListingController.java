package com.backend.controller.navbar;

import com.backend.entity.Application;
import com.backend.entity.Company;
import com.backend.entity.User;
import com.backend.service.ApplicationService;
import com.backend.service.CompanyService;
import com.backend.service.Impl.CompanyServiceImpl;
import com.backend.service.PostService;
import com.backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@AllArgsConstructor
public class JobListingController {
    private final CompanyService companyService;
    private final UserService userService;
    private final CompanyServiceImpl companyServiceImpl;
    private final PostService postService;
    private final ApplicationService applicationService;

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

    @GetMapping("/viewApplicants")
    public String viewApplicants(Model model , @RequestParam("id") Integer id ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.getUserbyEmail(username);
        Company company = user.getCompany() ;
        if (id == null) {
            return "error";
        }
        model.addAttribute("Applicants" , applicationService.findByPostId(id));
        model.addAttribute("currentPost" , companyService.getCompanyById(id)) ;
        model.addAttribute("userLoggedIn" , user);
        model.addAttribute("company", company);
        return "Company/applicants-table-view";
    }

    @GetMapping("/applicant")
    public String viewApplicant(Model model , @RequestParam("id") Integer id ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.getUserbyEmail(username);
        Company company = user.getCompany() ;
        if (id == null) {
            return "error";
        }
        Application application = applicationService.findById(id);
        model.addAttribute("applyForm" , application);
        model.addAttribute("company", company);
        return "Company/applicant-profile";
    }
    @PostMapping("/applicant")
    public String postApplicant(@ModelAttribute("applyForm") Application application) {
        applicationService.save(application);
        return "redirect:/applicant?id=" + application.getId();
    }

    @PostMapping("/updateStatus")
    public String updateStatus(@RequestParam("id") int id, @RequestParam("status") String status, @RequestParam("interviewDate") String interviewDate, @RequestParam("interviewTime") String interviewTime) {
        Application application = applicationService.findById(id);
        if (application != null) {
            application.setStatus(status);
            if(application.getStatus().equals("Interview") && (interviewDate == null || interviewTime == null)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime interviewStartDateTime = LocalDateTime.parse(interviewDate + " " + interviewTime, formatter);
                application.setInterviewStartTime(interviewStartDateTime);
            }
            applicationService.save(application);
            applicationService.updateStatus(id, status);
        }
        return "redirect:/applicant?id=" + id;
    }
}
