package com.backend.controller.home;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.backend.entity.*;
import com.backend.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.backend.dto.UserDto;
import com.backend.repository.PostRepository;

import lombok.AllArgsConstructor;
import org.springframework.web.servlet.handler.AbstractUrlHandlerMapping;

@Controller
@AllArgsConstructor
@SessionAttributes("userdto")
public class HomeController {
    private final LocationService locationService;
    private final LevelService levelService;
    private final CategoryService categoryService;
    private final PostService postService;
    private final UserService userService;
    private final ApplicationService applicationService;

    @ModelAttribute("userdto")
    public UserDto userDto() {
        return new UserDto();
    }

    @GetMapping("/error")
    public String showErrorPage() {
        return "error";
    }
    @GetMapping("/home")
    public String showHomeForm(@ModelAttribute("userdto") UserDto userDto, Model model) {
        List<Location> locations = locationService.getAllLocations();
        List<Level> levels = levelService.getAllLevel();
        List<Category> categories = categoryService.getAllCategories();
        List<Post> posts= postService.getAllPosts();
        List<Post> posts_newest = postService.getAllPostsOrderByDatetime();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            User userLoggedIn = userService.getUserbyEmail(userDetails.getUsername());
            model.addAttribute("userLoggedIn", userLoggedIn);
        }
        for (Post post : posts) {
            if(post.getCategories().isEmpty()){
                Category category1 = new Category();
                category1.setName("N/A");
                post.getCategories().add(category1);

                Category category2 = new Category();
                category2.setName("N/A");
                post.getCategories().add(category2);
            }
            else if(post.getCategories().size() == 1){
                Category category = post.getCategories().get(0);
                if(category.getName() == null){
                    category.setName("N/A");
                }

                Category category2 = new Category();
                category2.setName("N/A");
                post.getCategories().add(category2);
            }
            if (post.getLocation() == null) {
                post.setLocation(new Location());
            }
            if (post.getLocation().getName() == null) {
                post.getLocation().setName("N/A");  // Set the name to "N/A" if it's null
            }
        }
        model.addAttribute("posts", posts);
        model.addAttribute("locations", locations);
        model.addAttribute("categories", categories);
        model.addAttribute("levels", levels);
        model.addAttribute("posts_newest", posts_newest) ;
        return "index";
    }

    @GetMapping("/dashboard")
    public String showDashboard(@ModelAttribute("userdto") UserDto userDto, Model model) {
        Authentication authen = SecurityContextHolder.getContext().getAuthentication();
        User userLoggedIn = null;
        if (authen != null && authen.getPrincipal() instanceof UserDetails userDetails) {
            userLoggedIn = userService.getUserbyEmail(userDetails.getUsername());
        }
        if (userLoggedIn == null) {
            userLoggedIn = new User();
        }
        if(userLoggedIn.getRole().equals("company"))
        {
            return "Company/index";
        }
        return "applicant";
    }

    @GetMapping("/profile")
    public String showAboutPage(Authentication authentication, Model model) {
        User user = userService.getUserbyEmail(authentication.getName());
        model.addAttribute("user", user);
        if(user.getRole().equals("company"))
        {
            return "Company/profile";
        }

        return "profile";
    }

    @GetMapping("/allApplicants")
    public String showAllApplicants(Model model , Authentication auth) {
        User user = userService.getUserbyEmail(auth.getName());
        List<Application> applications = applicationService.findAllByPostCompany(user.getCompany().getId());
        System.out.println("Applcation are : " + applications.size());
        model.addAttribute("applications", applications);
        model.addAttribute("user", user);
        return "Company/all-applicants";
    }

}
