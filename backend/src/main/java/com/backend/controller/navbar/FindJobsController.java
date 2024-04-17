package com.backend.controller.navbar;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.backend.dto.UserDto;
import com.backend.entity.Category;
import com.backend.entity.Level;
import com.backend.entity.Location;
import com.backend.entity.Post;
import com.backend.service.CategoryService;
import com.backend.service.LevelService;
import com.backend.service.LocationService;
import com.backend.service.PostService;
import com.backend.service.UserService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class FindJobsController {
    private UserService userService;
    private PostService postService;
    private CategoryService     categoryService;
    private LocationService locationService ;
    private LevelService levelService;
    @ModelAttribute("userdto")
    public UserDto userResgistrationDto() {
        return new UserDto();
    }

    @GetMapping("/findjobs")
    public String showRegistrationForm(Model model, @RequestParam(defaultValue = "1") int page) {
        Pageable pageable = PageRequest.of(page - 1, 6, Sort.by("datePosted").descending());
        Page<Post> postPage = postService.getAllPosts(pageable);
        List<Level> levels = levelService.getAllLevel() ;
        List<Category> categories = categoryService.getAllCategories();
        List<Location> locations = locationService.getAllLocations();
        model.addAttribute("categories", categories);
        model.addAttribute("page", postPage);
        model.addAttribute("locations", locations);
        model.addAttribute("levels", levels);
        return "find-jobs";
    }
}