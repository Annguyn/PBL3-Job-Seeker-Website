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
import com.backend.entity.ProgramingLanguage;
import com.backend.service.CategoryService;
import com.backend.service.LevelService;
import com.backend.service.LocationService;
import com.backend.service.PostService;
import com.backend.service.UserService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@AllArgsConstructor
public class FindJobsController {
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
                // handle case when sort parameter is not "maxSalary"
                // for example, you can default to sorting by datePosted
                pageable = PageRequest.of(page - 1, 6, Sort.by("datePosted").descending());
            }
            Page<Post> postPage = postService.getAllPosts(pageable);
            List<Level> levels = levelService.getAllLevel();
            List<Category> categories = categoryService.getAllCategories();
            List<Location> locations = locationService.getAllLocations();
            model.addAttribute("categories", categories);
            model.addAttribute("page", postPage);
            model.addAttribute("locations", locations);
            model.addAttribute("levels", levels);
            return "find-jobs";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/jobdetails")
    public String JobDetailsForm(Model model, @RequestParam(required = false) Integer id) {
        if (id == null) {
            return "job-descriptions";
        }
        try {
            Post post = postService.getPostById(id);
            model.addAttribute("post", post);
            return "job-descriptions";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }
}