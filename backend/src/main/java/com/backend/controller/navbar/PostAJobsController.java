package com.backend.controller.navbar;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.backend.dto.PostDto;
import com.backend.dto.UserDto;
import com.backend.entity.Category;
import com.backend.entity.Level;
import com.backend.entity.Location;
import com.backend.entity.Post;
import com.backend.entity.ProgramingLanguage;
import com.backend.entity.User;
import com.backend.service.CategoryService;
import com.backend.service.LevelService;
import com.backend.service.LocationService;
import com.backend.service.PostService;
import com.backend.service.ProgrammingLanguageService;
import com.backend.service.UserService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class PostAJobsController {
    private UserService userService;
    private PostService postService;
    private CategoryService categoryService;
    private LocationService locationService;
    private LevelService levelService;
    private ProgrammingLanguageService programingLanguageService ;

    @GetMapping("/postjobs")
    public String getPostJobsForm(@ModelAttribute("userDto") UserDto userDto , Model model) {
        List<Level> levels = levelService.getAllLevel();
        List<Category> categories = categoryService.getAllCategories();
        List<Location> locations = locationService.getAllLocations();
        List<ProgramingLanguage> programingLanguages = programingLanguageService.getAllProgramingLanguages() ;
        User user = userService.getUserbyEmail(userDto.getEmail()) ;
        model.addAttribute("userUsing" , user )  ;
        model.addAttribute("locations", locations);
        model.addAttribute("levels", levels);
        model.addAttribute("categories", categories);
        model.addAttribute("programingLanguages" , programingLanguages) ;
        return "Company/dashboard-company-post-a-job";
    }

    @PostMapping("/postjobs")
    public String postJobs(@ModelAttribute("postDto") PostDto postDto, @ModelAttribute("userDto") UserDto userDto,
            BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "Company/dashboard-company-post-a-job";
        }

        User user = userService.getUserbyEmail(userDto.getEmail());
        try {
            Post post = new Post(
                    user.getCompany().getId(),
                    postDto.getMaxSalary(),
                    postDto.getMinSalary(),
                    postDto.getPhoneNumber(),
                    postDto.getEmail(),
                    postDto.getContent(),
                    postDto.getImages(),
                    postDto.getExperience());
            postService.save(post);
            redirectAttributes.addFlashAttribute("successMessage", "Job posted successfully");
            return "redirect:/dashboard";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "An error occurred while posting the job");
            return "redirect:/postjobs";
        }
    }
}
