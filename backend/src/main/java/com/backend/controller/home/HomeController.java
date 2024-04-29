package com.backend.controller.home;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.backend.dto.UserDto;
import com.backend.entity.Category;
import com.backend.entity.Level;
import com.backend.entity.Location;
import com.backend.entity.Post;
import com.backend.entity.User;
import com.backend.repository.PostRepository;
import com.backend.service.CategoryService;
import com.backend.service.LevelService;
import com.backend.service.LocationService;
import com.backend.service.PostService;
import com.backend.service.UserService;

import lombok.AllArgsConstructor;
@Controller
@AllArgsConstructor
@SessionAttributes("userdto")
public class HomeController {
    private final LocationService locationService;
    private final LevelService levelService;
    private final CategoryService categoryService;
    private final PostService postService;
    private final UserService userService;
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
        User user = userService.getUserbyEmail(userDto.getEmail());
        if(user.getRole().equals("company"))
        {
            return "Company/index";
        }
        return "applicant";
    }
}
