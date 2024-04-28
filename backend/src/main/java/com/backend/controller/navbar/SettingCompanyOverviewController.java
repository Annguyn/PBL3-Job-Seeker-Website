package com.backend.controller.navbar;

import com.backend.entity.Company;
import com.backend.entity.User;
import com.backend.service.CompanyService;
import com.backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@AllArgsConstructor
public class SettingCompanyOverviewController {
    private final UserService userService;
    private final CompanyService companyService;


    @GetMapping("/settings")
    public String getSettingCompanyOverview(Model model) {
        User userLoggedIn = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            userLoggedIn = userService.getUserbyEmail(userDetails.getUsername());
            model.addAttribute("userLoggedIn", userLoggedIn);
        }
        assert userLoggedIn != null;
        model.addAttribute("companyLoggedIn", userLoggedIn.getCompany());

        return "Company/settings-overview";
    }@PostMapping("/settings")
    public String postSettingCompanyOverview(@ModelAttribute("companyLoggedIn") Company formCompany, @RequestParam("avatarFile") MultipartFile avatarFile, Model model) throws IOException {
        // Fetch the existing company from the database
        User userLoggedIn = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            userLoggedIn = userService.getUserbyEmail(userDetails.getUsername());
        }
        assert userLoggedIn != null;

        Company existingCompany = userLoggedIn.getCompany();
        existingCompany.setCompanyWebsite(formCompany.getCompanyWebsite());
        existingCompany.setName(formCompany.getName());
        existingCompany.setProfileDescription(formCompany.getProfileDescription());

        // Convert MultipartFile to byte[] and set it to the avatar field
        if (!avatarFile.isEmpty()) {
            existingCompany.setAvatar(avatarFile.getBytes());
        }

        companyService.saveCompany(existingCompany);

        model.addAttribute("successMessage", "Settings saved successfully");

        return "redirect:/home";
    }

}
