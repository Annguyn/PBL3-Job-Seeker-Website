package com.backend.controller.navbar;

import java.util.List;

import org.springframework.boot.actuate.autoconfigure.metrics.SystemMetricsAutoConfiguration;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class PostAJobsController {
    private final SystemMetricsAutoConfiguration systemMetricsAutoConfiguration;

    @ModelAttribute("userdto")
    public UserDto userDto() {
        return new UserDto();
    }
    @ModelAttribute("postdto")
    public PostDto postDto() {
        return new PostDto();
    }
    private UserService userService;
    private PostService postService;
    private CategoryService categoryService;
    private LocationService locationService;
    private LevelService levelService;
    private ProgrammingLanguageService programingLanguageService ;

    @GetMapping("/postjobs")
    public String getPostJobsForm(@ModelAttribute("userdto") UserDto userDto , Model model) {
        List<Level> levels = levelService.getAllLevel();
        List<Category> categories = categoryService.getAllCategories();
        List<Location> locations = locationService.getAllLocations();
        List<ProgramingLanguage> programingLanguages = programingLanguageService.getAllProgramingLanguages() ;
        User user = userService.getUserbyEmail(userDto.getEmail()) ;
        System.out.println("User is logging : " + user.getEmail());
        model.addAttribute("postdto",new PostDto()) ;
        model.addAttribute("userUsing" , user )  ;
        model.addAttribute("locations", locations);
        model.addAttribute("levels", levels);
        model.addAttribute("categories", categories);
        model.addAttribute("programingLanguages" , programingLanguages) ;
        return "Company/post-a-job";
}
    @GetMapping("/postjobs/step2")
    public String getPostJobsStep2Form(@ModelAttribute("userdto") UserDto userDto , @ModelAttribute("postdto") PostDto postDto ,  Model model){
        
        return "Company/post-a-job-job-description" ;
    }
    @PostMapping("/postjobs")
    public String postJobs(@ModelAttribute("postdto") PostDto postDto, @ModelAttribute("userdto") UserDto userDto,
                           BindingResult result, RedirectAttributes redirectAttributes, Model model) {
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
            post.setProgramingLanguages(programingLanguageService.getProgramingLanguageByIds(postDto.getProgrammingLanguageIds()));
            post.setCategories(categoryService.getCategoriesByIds(postDto.getCategoryIds()));
            post.setContent(postDto.getContent());
            postService.save(post);
            System.out.println("Post saved : " + post.getMinSalary());
            model.addAttribute("successMessage", "Job posted successfully");
            return "Company/post-a-job"; // return the current view
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred while posting the job");
            return "Company/post-a-job"; // return the current view
        }
    }
}
