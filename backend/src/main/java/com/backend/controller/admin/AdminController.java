package com.backend.controller.admin;


import com.backend.entity.Companies;
import com.backend.entity.Company;
import com.backend.entity.University;
import com.backend.entity.User;
import com.backend.repository.CompaniesRepository;
import com.backend.repository.CompanyRepository;
import com.backend.repository.UniversityRepository;
import com.backend.repository.UserRepository;
import com.backend.service.Impl.CompaniesService;
import com.backend.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompaniesRepository companiesRepository;
    @Autowired
    private CompaniesService companiesService;
    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private UniversityService universityService;

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "Admin/user";
    }

    @PostMapping("/users/edit/{id}")
    public String updateUser(@PathVariable int id, @ModelAttribute User user) {
        userRepository.save(user);
        return "redirect:/admin/users";
    }

    @PostMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
        return "redirect:/admin/users";
    }
    @GetMapping("/companies")
    public String getCompanies(Model model) {
        model.addAttribute("companies", companiesRepository.findAll());
        return "Admin/companies";
    }

    @PostMapping("/companies/add")
    public String addCompany() {
        Companies company = new Companies();
        companiesRepository.save(company);
        return "redirect:/admin/companies";
    }
    @PostMapping("/companies/save/{id}")
    public String saveCompany(@PathVariable int id, @RequestParam(value = "name") String name, @RequestParam(value = "image", required = false) MultipartFile imageFile) {
        try {
            Companies company = companiesService.findById(id);
            if (company == null) {
                company = new Companies();
            }
            company.setName(name);
            if (imageFile != null && !imageFile.isEmpty()) {
                company.setImage(imageFile.getBytes());
            }
            else {
                company.setImage(new byte[0]);
            }
            companiesRepository.save(company);
        } catch (IOException e) {
            // Handle the exception
        }
        return "redirect:/admin/companies";
    }

    @GetMapping("/companies/delete/{id}")
    public String deleteCompany(@PathVariable int id) {
        companiesRepository.deleteById(id);
        return "redirect:/admin/companies";
    }

    @GetMapping("/universities")
    public String getUniversities(Model model) {
        model.addAttribute("universities", universityRepository.findAll());
        model.addAttribute("university", new University());  // Add this line
        return "Admin/university";
    }

    @PostMapping("/universities/save/{id}")
    public String saveUniversity(@PathVariable(required = false) Integer id,  @RequestParam(value = "image", required = false) MultipartFile imageFile) {
        try {
            University university = null;
            if (id != null) {
                university = universityService.findById(id);
            }
            if (university == null) {
                university = new University();
            }
            if (imageFile != null && !imageFile.isEmpty()) {
                university.setPhoto(imageFile.getBytes());
            }

            universityRepository.save(university);
        } catch (IOException e) {
            // Handle the exception
        }
        return "redirect:/admin/universities";
    }
    @PostMapping("/universities/add")
    public String addUniversity() {
        University university = new University();
        university.setName("New University");
        university.setPhoto(new byte[0]);
        universityRepository.save(university);
        return "redirect:/admin/universities";
    }

    @PostMapping("/users/add")
    public String addUser(@ModelAttribute User user) {
        userRepository.save(user);
        return "redirect:/admin/users";
    }
    @PostMapping("/universities/delete/{id}")
    public String deleteUniversity(@PathVariable int id) {
        universityRepository.deleteById(id);
        return "redirect:/admin/universities";
    }
}