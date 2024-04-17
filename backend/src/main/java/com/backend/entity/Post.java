package com.backend.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "recruitment_post")
@Data
@NoArgsConstructor
public class Post {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "company_id")
    private int companyId;

    @Column(name = "max_salary")
    private BigDecimal maxSalary;

    @Column(name = "min_salary")
    private BigDecimal minSalary;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "images", columnDefinition = "TEXT")
    private String images;

    @Column(name = "experience")
    private String experience;

    // @Column(name ="location_id")
    // private int locationId;

    @Column(name = "date_posted")
    private LocalDateTime datePosted;

    @OneToOne
    @JoinColumn(name = "level_id")
    private Level level;

    @OneToOne
    @JoinColumn(name = "languages_id")
    private Language language;

    @OneToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToMany
    @JoinTable(name = "post_category", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    @ManyToMany
    @JoinTable(name = "post_programming_languages", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "programming_languages_id"))
    private List<ProgramingLanguage> programingLanguages;

    public Post(int CompanyId, BigDecimal maxSalary, BigDecimal minSalary, String phoneNumber, String email,
            String content, String images, String experience) {
        this.companyId = CompanyId;
        this.maxSalary = maxSalary;
        this.minSalary = minSalary;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.content = content;
        this.images = images;
        this.experience = experience;
    }
}