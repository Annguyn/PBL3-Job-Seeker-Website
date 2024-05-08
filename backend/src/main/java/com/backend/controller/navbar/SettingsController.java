package com.backend.controller.navbar;

import com.backend.entity.*;
import com.backend.service.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

    @Controller
    public class SettingsController {

        private final UserService userService;
        private final LocationService locationService;
        private final CompanyService companyService;
        private final UniversityService universityService;
        private final EducationService educationService;

        public SettingsController(UserService userService, LocationService locationService, CompanyService companyService, UniversityService universityService,  EducationService educationService) {
            this.userService = userService;
            this.locationService = locationService;
            this.companyService = companyService;
            this.universityService = universityService;
            this.educationService = educationService;
        }

        @GetMapping("/settings")
        public String getSettings(@RequestParam(name = "type", required = false) String type,
                                  Model model) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            List<University> universities = universityService.getAllUniversities();
//            model.addAttribute("MajorUni", "Computer Science");
//            model.addAttribute("Degree", "Bachelor's");
            model.addAttribute("UniversityId", universities.get(1).getId());
            if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
                User userLoggedIn = userService.getUserbyEmail(userDetails.getUsername());
                if (userLoggedIn.getRole().equals("company")) {
                    model.addAttribute("companyLoggedIn", userLoggedIn.getCompany());
                    return "Company/settings-overview";
                } else {
                    List<Location> locations = locationService.getAllLocations();
                    model.addAttribute("universities", universities);
                    model.addAttribute("user", userLoggedIn);
                    model.addAttribute("locations", locations);
                    return "settings-account-settings";
                }
            }
            return "redirect:/login";
        }
        @PostMapping("/settings")
        public String postSettings(@ModelAttribute("companyLoggedIn") Company formCompany, @ModelAttribute("user") User user,
                                   Authentication auth, @RequestParam(value = "avatarFile" ,required = false) MultipartFile avatarFile, Model model,
                                   @RequestParam(value = "UniversityId" ,required = false) Integer UniversityId,
                                   @RequestParam(value = "MajorUni" ,required = false) String MajorUni,
                                   @RequestParam(value = "Degree" ,required = false) String Degree,
                                   @RequestParam(value = "startDate" ,required = false) String startDate,
                                   @RequestParam(value = "endDate" ,required = false) String endDate) throws IOException, IOException {
            User currentUser = userService.getUserbyEmail(auth.getName());
            if (avatarFile != null && !avatarFile.isEmpty()) {
                currentUser.setPhoto(avatarFile.getBytes());
            }
            if (currentUser.getRole().equals("company")) {
                Company existingCompany = currentUser.getCompany();
                existingCompany.setCompanyWebsite(formCompany.getCompanyWebsite());
                existingCompany.setName(formCompany.getName());
                existingCompany.setProfileDescription(formCompany.getProfileDescription());
                companyService.saveCompany(existingCompany);
                model.addAttribute("successMessage", "Company settings saved successfully");
            } else {
                if(UniversityId != null) {
                    University university = universityService.findById(UniversityId);
                    Education education = new Education();
                    education.setUniversity(university);
                    education.setUser(currentUser) ;
                    education.setMajor(MajorUni);
                    education.setCertificateDegreeName(Degree);
                    education.setStartDate(startDate);
                    education.setGradDate(endDate);
                    educationService.save(education);
                    if(user.getEducationDetail() != null){
                        user.getEducationDetail().add(education);
                    }
                    else {
                        List<Education> educationList = new ArrayList<>();
                        educationList.add(education);
                        user.setEducationDetail(educationList);
                    }
                }
                else {
                    System.out.print("UniversityId is null");
                }
                if(user.getUserDisplayName() != null) {
                    currentUser.setUserDisplayName(user.getUserDisplayName());
                }
                if(user.getContactNumber() != null) {
                    currentUser.setContactNumber(user.getContactNumber());
                }
                if(user.getEmail() != null) {
                    currentUser.setEmail(user.getEmail());
                }
                if(user.getDob() != null) {
                    currentUser.setDob(user.getDob());
                }
                if(user.getGender() != null) {
                    currentUser.setGender(user.getGender());
                }
                if(user.getGithub() != null) {
                    currentUser.setGithub(user.getGithub());
                }
                if(user.getLinkedin() != null) {
                    currentUser.setLinkedin(user.getLinkedin());
                }
                if(user.getMajor() != null) {
                    currentUser.setMajor(user.getMajor());
                }
                if(user.getExperienceInYears() != null) {
                    currentUser.setExperienceInYears(user.getExperienceInYears());
                }
                if(user.getLocation() != null ) {
                    currentUser.setLocation(locationService.getLocationById(user.getLocation().getId()));
                }
                if(user.getRole() != null) {
                    currentUser.setRole(user.getRole());
                }
                if(user.getBio() != null) {
                    currentUser.setBio(user.getBio());
                }
                userService.save(currentUser);
                model.addAttribute("successMessage", "User settings saved successfully");
            }
            return "redirect:/home";
        }
    }

