package com.backend.controller.home;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.backend.repository.PostRepository;
import com.backend.service.CategoryService;
import com.backend.service.LevelService;
import com.backend.service.LocationService;
import com.backend.service.PostService;
import lombok.AllArgsConstructor;
@Controller
@AllArgsConstructor
@SessionAttributes("userdto")
public class HomeController {
    private final LocationService locationService;
    private final LevelService levelService;
    private final CategoryService categoryService;
    private final PostService postService;
    @ModelAttribute("userdto")
    public UserDto userDto() {
        return new UserDto();
    }
    @GetMapping("/home")
    public String showHomeForm(@ModelAttribute("userdto") UserDto userDto, Model model) {
        List<Location> locations = locationService.getAllLocations();
        List<Level> levels = levelService.getAllLevel();
        List<Category> categories = categoryService.getAllCategories();
        List<Post> posts= postService.getAllPosts();
        model.addAttribute("posts", posts);
        model.addAttribute("locations", locations);
        model.addAttribute("categories", categories);
        model.addAttribute("levels", levels);
        return "index";
    }
}
