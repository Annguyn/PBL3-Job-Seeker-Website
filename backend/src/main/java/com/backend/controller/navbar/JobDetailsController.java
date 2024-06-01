package com.backend.controller.navbar;

import com.backend.dto.ApplicationDTO;
import com.backend.entity.*;
import com.backend.service.ApplicationService;
import com.backend.service.PostService;
import com.backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
@AllArgsConstructor
public class JobDetailsController {

    private final UserService userService;
    private final PostService postService;
    private final ApplicationService applicationService;

    @GetMapping("/jobdetails")
    public String JobDetailsForm(@ModelAttribute("ApplicationDTO") ApplicationDTO jobApplicationDto, Model model, @RequestParam("id") Integer id) {
        if (id == null) {
            return "job-descriptions";
        }
        User userLoggedIn = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            userLoggedIn = userService.getUserbyEmail(userDetails.getUsername());
        }
        if (userLoggedIn == null) {
            userLoggedIn = new User();
        }
        List<Comment> comments = postService.getPostById(id).getComments();
        model.addAttribute("comments", comments);
        model.addAttribute("userLoggedIn", userLoggedIn);
        try {
            Post post = postService.getPostById(id);
            if (post.getLocation() == null) {
                post.setLocation(new Location());
            }
            if (post.getLocation().getName() == null) {
                post.getLocation().setName("N/A");  // Set the name to "N/A" if it's null
            }
            model.addAttribute("post", post);
            return "job-descriptions";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }
    @PostMapping("/jobdetails")
    public String applyJob(@ModelAttribute("ApplicationDTO") ApplicationDTO jobApplicationDto , @RequestParam("id") Integer postId) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userLoggedIn = null;
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            userLoggedIn = userService.getUserbyEmail(userDetails.getUsername());
        }
        if (userLoggedIn == null) {
            userLoggedIn = new User();
        }
        byte[] resumeBytes;
        if (jobApplicationDto.getResume() != null) {
            resumeBytes = jobApplicationDto.getResume().getBytes();
        } else {
            resumeBytes = new byte[0];
        }
        Application application = new Application(
                userLoggedIn,
                postService.getPostById(postId),
                jobApplicationDto.getFullName(),
                jobApplicationDto.getEmailAddress(),
                jobApplicationDto.getPhoneNumber(),
                jobApplicationDto.getJobTitle(),
                jobApplicationDto.getLinkedInUrl(),
                jobApplicationDto.getPortfolioUrl(),
                jobApplicationDto.getAdditionalInformation(),
                resumeBytes );
        // Save the application
        applicationService.save(application);
        return "redirect:/home";
    }
}
