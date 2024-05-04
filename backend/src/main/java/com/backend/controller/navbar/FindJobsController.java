package com.backend.controller.navbar;

import java.io.IOException;
import java.util.List;

import com.backend.entity.*;
import com.backend.service.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.backend.dto.UserDto;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@AllArgsConstructor
public class FindJobsController {
    private final ApplicationService applicationService;
    private final CompanyService companyService;
    private UserService userService;
    private PostService postService;
    private CategoryService categoryService;
    private LocationService locationService;
    private LevelService levelService;

    @ModelAttribute("userdto")
    public UserDto userResgistrationDto() {
        return new UserDto();
    }

    @GetMapping("/findjobs")
    public String showFindJobsForm(Model model,
                                   @RequestParam(defaultValue = "1") int page,
                                   @RequestParam(required = false) String sort) {
        try {
            Pageable pageable;
            if ("salary".equals(sort)) {
                pageable = PageRequest.of(page - 1, 6, Sort.by("maxSalary").descending());
            } else {
                pageable = PageRequest.of(page - 1, 6, Sort.by("datePosted").descending());
            }
            Page<Post> postPage = postService.getAllPosts(pageable);
            for (Post post : postPage) {
                if (post.getLocation() == null) {
                    post.setLocation(new Location());
                }
                if (post.getLocation().getName() == null) {
                    post.getLocation().setName("N/A");  // Set the name to "N/A" if it's null
                }
            }
            List<Level> levels = levelService.getAllLevel();
            List<Category> categories = categoryService.getAllCategories();
            List<Location> locations = locationService.getAllLocations();
            model.addAttribute("categories", categories);
            model.addAttribute("page", postPage);
            model.addAttribute("locations", locations);
            model.addAttribute("levels", levels);
            return "find-jobs";
        } catch(Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }
    @GetMapping("/jobdetails")
    public String JobDetailsForm(Model model, @RequestParam("id") Integer id) {
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
    public String applyJob(
            @RequestParam("id") Integer postId,
            @RequestParam("fullName") String fullName,
            @RequestParam("emailAddress") String emailAddress,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("JobTitle") String jobTitle,
            @RequestParam("linkedInUrl") String linkedInUrl,
            @RequestParam("portfolioUrl") String portfolioUrl,
            @RequestParam("additionalInformation") String additionalInformation,
            @RequestParam("resume") MultipartFile resume) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userLoggedIn = null;
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            userLoggedIn = userService.getUserbyEmail(userDetails.getUsername());
        }
        if (userLoggedIn == null) {
            userLoggedIn = new User();  // Add a default User object if userLoggedIn is null
        }

        Application application = new Application(
                userLoggedIn,
                postService.getPostById(postId),
                fullName,
                emailAddress,
                phoneNumber,
                jobTitle,
                linkedInUrl,
                portfolioUrl,
                additionalInformation,
                resume.getBytes());
        // Save the application
         applicationService.save(application);
        return "redirect:/home";
    }

    @GetMapping("/findCompany")
    public String findCompany(Model model) {
        try {
            List<Company> companies = companyService.getAllCompanies();
            model.addAttribute("companies", companies);
            return "company-search";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }
    @GetMapping("/")
    public String getHighChart() {
        return "chart";
    }

    @GetMapping("/aboutCompany")
    public String aboutCompany(Model model , @RequestParam("id") Integer id ) {
        if (id == null) {
            return "find-jobs";
        }
        try {
            Company company = companyService.getCompanyById(id);
            model.addAttribute("company", company);
            return "company-profile";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }
}