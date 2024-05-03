package com.backend.controller.navbar;

import com.backend.entity.Company;
import com.backend.entity.Location;
import com.backend.entity.User;
import com.backend.service.CompanyService;
import com.backend.service.LocationService;
import com.backend.service.UserService;
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
import java.util.List;

    @Controller
    public class SettingsController {

        private final UserService userService;
        private final LocationService locationService;
        private final CompanyService companyService;

        public SettingsController(UserService userService, LocationService locationService, CompanyService companyService) {
            this.userService = userService;
            this.locationService = locationService;
            this.companyService = companyService;
        }


        @GetMapping("/settings")
        public String getSettings(Model model) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
                User userLoggedIn = userService.getUserbyEmail(userDetails.getUsername());
                if (userLoggedIn.getRole().equals("company")) {
                    model.addAttribute("companyLoggedIn", userLoggedIn.getCompany());
                    return "Company/settings-overview";
                } else {
                    List<Location> locations = locationService.getAllLocations();
                    model.addAttribute("user", userLoggedIn);
                    model.addAttribute("locations", locations);
                    return "settings-account-settings";
                }
            }
            return "redirect:/login"; // Redirect to login if user is not authenticated
        }

        @PostMapping("/settings")
        public String postSettings(@ModelAttribute("companyLoggedIn") Company formCompany, @ModelAttribute("user") User user,
                                   Authentication auth, @RequestParam("avatarFile") MultipartFile avatarFile, Model model) throws IOException, IOException {
            User currentUser = userService.getUserbyEmail(auth.getName());
            if (currentUser.getRole().equals("company")) {
                Company existingCompany = currentUser.getCompany();
                existingCompany.setCompanyWebsite(formCompany.getCompanyWebsite());
                existingCompany.setName(formCompany.getName());
                existingCompany.setProfileDescription(formCompany.getProfileDescription());
                if (!avatarFile.isEmpty()) {
                    existingCompany.setAvatar(avatarFile.getBytes());
                }
                companyService.saveCompany(existingCompany);
                model.addAttribute("successMessage", "Company settings saved successfully");
            } else {
                if (!avatarFile.isEmpty()) {
                    currentUser.setPhoto(avatarFile.getBytes());
                }
                currentUser.setUserDisplayName(user.getUserDisplayName());
                currentUser.setContactNumber(user.getContactNumber());
                currentUser.setEmail(user.getEmail());
                currentUser.setDob(user.getDob());
                currentUser.setGender(user.getGender());
                currentUser.setGithub(user.getGithub());
                currentUser.setLinkedin(user.getLinkedin());
                currentUser.setMajor(user.getMajor());
                currentUser.setExperienceInYears(user.getExperienceInYears());
                currentUser.setLocation(locationService.getLocationById(user.getLocation().getId()));
                currentUser.setRole(user.getRole());
                currentUser.setBio(user.getBio());
                userService.save(currentUser);
                model.addAttribute("successMessage", "User settings saved successfully");
            }
            return "redirect:/home";
        }
    }

