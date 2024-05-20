package com.backend.controller.navbar;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.backend.entity.*;
import com.backend.service.*;
import jnr.ffi.Struct;
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
    private final CommentService commentService;
    private UserService userService;
    private PostService postService;
    private CategoryService categoryService;
    private LocationService locationService;
    private LevelService levelService;

    @ModelAttribute("userdto")
    public UserDto userResgistrationDto() {
        return new UserDto();
    }

    @PostMapping("/findjobs")
    public String showFindJobsForm(
            @RequestParam("keySearch") String keySearch,
            @RequestParam(name = "sort", required = false) String sort,
            @RequestParam(name = "filterCategory", required = false) String filterCategory,
            @RequestParam(defaultValue = "1") int page,
            Model model) {

        StringBuilder redirectUrl = new StringBuilder("redirect:/findjobs?keySearch=").append(keySearch);

        if (sort != null) {
            redirectUrl.append("&sort=").append(sort);
        }
        if (filterCategory != null) {
            redirectUrl.append("&filterCategory=").append(filterCategory);
        }
        redirectUrl.append("&page=").append(page);

        return redirectUrl.toString();
    }

    @GetMapping("/findjobs")
    public String showFindJobsForm(Model model,
                                   @RequestParam(defaultValue = "1") int page,
                                   @RequestParam(name = "sort" ,required = false) String sort,
                                   @RequestParam(name="filterCategory" , required = false) String filterCategory,
                                   @RequestParam(name="keySearch" , required = false) String keySearch) {

        List<String> categories = null;
        if (filterCategory != null && !filterCategory.isEmpty()) {
            categories = Arrays.asList(filterCategory.split(","));
        }
        try {
            Pageable pageable;
            if ("salary".equals(sort)) {
                pageable = PageRequest.of(page - 1, 6, Sort.by("maxSalary").descending());
            } else {
                pageable = PageRequest.of(page - 1, 6, Sort.by("datePosted").descending());
            }
            Page<Post> postPage = postService.getAllPosts(pageable, categories,keySearch);
            int numberPost = postPage.getNumberOfElements();
            if(categories != null) {
                numberPost = postService.getAllPosts().size(); ;
            }
            model.addAttribute("numberPost", numberPost);
            for (Post post : postPage) {
                if (post.getLocation() == null) {
                    post.setLocation(new Location());
                }
                if (post.getLocation().getName() == null) {
                    post.getLocation().setName("N/A");
                }
            }

            List<Level> allLevels = levelService.getAllLevel();
            List<Category> allCategories = categoryService.getAllCategories();
            List<Location> locations = locationService.getAllLocations();
            model.addAttribute("categories", allCategories);
            model.addAttribute("page", postPage);
            model.addAttribute("locations", locations);
            model.addAttribute("levels", allLevels);
            return "find-jobs";
        } catch(Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "find-jobs";
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
    @PostMapping("/addComment")
    public String addComment(@RequestParam String content, @RequestParam Integer postId, Authentication authentication) {
        User user = userService.getUserbyEmail(authentication.getName());
        Post post = postService.getPostById(postId);

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setUser(user);
        comment.setPost(post);

        commentService.saveComment(comment);

        return "redirect:/jobdetails?id=" + postId;
    }
    @PostMapping("/deleteComment")
    public String deleteComment(@RequestParam Long commentId, Authentication authentication) {
        User user = userService.getUserbyEmail(authentication.getName());
        Comment comment = commentService.getCommentById(commentId);

        commentService.deleteComment(comment);

        return "redirect:/jobdetails?id=" + comment.getPost().getId();
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