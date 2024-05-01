package com.backend.controller.navbar;

import com.backend.entity.Location;
import com.backend.entity.User;
import com.backend.service.LocationService;
import com.backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class UserOverview {
    private final UserService userService;
    private final LocationService locationService;

//    @GetMapping("/profileUser")
//    public String getUserOverview( Authentication auth , Model model , Principal principal) {
//        User user = userService.getUserbyEmail(auth.getName());
//        if (principal == null) {
//            return "redirect:/login";
//        }
//        model.addAttribute("user", user);
//        return "profile";
//    }

    @GetMapping("/profileSettingUser")
    public String getSettingUserOverview( Authentication auth , Model model) {
        User user = userService.getUserbyEmail(auth.getName());
        List<Location> locations  = locationService.getAllLocations() ;
        model.addAttribute("user", user);
        model.addAttribute("locations", locations);
        return "settings-account-settings";
    }
    @PostMapping("/profileSettingUser")
    public String postSettingUserOverview(@ModelAttribute("user") User user, Authentication auth, Model model, @RequestParam("avatarFile") MultipartFile avatarFile) throws IOException {
        User currentUser = userService.getUserbyEmail(auth.getName());
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
        System.out.println("Location ID: " + user.getLocation());
        currentUser.setLocation(locationService.getLocationById(user.getLocation().getId()));
        System.out.println("Current User Location " + currentUser.getLocation());
        currentUser.setRole(user.getRole());
        currentUser.setBio(user.getBio());

        userService.save(currentUser);
        model.addAttribute("user", currentUser);
        return "redirect:/profileUser";
    }

    @GetMapping("/jobApplied")
    public String getJobApplied( Authentication auth , Model model) {
        User user = userService.getUserbyEmail(auth.getName());
        model.addAttribute("user", user);
        return "applicant-history";
    }
}
