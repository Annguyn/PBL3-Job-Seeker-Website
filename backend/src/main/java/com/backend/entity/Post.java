package com.backend.entity;

import java.math.BigDecimal;
import java.util.List;

import org.aspectj.lang.annotation.control.CodeGenerationHint;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
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

    @Column(name ="company_id")
    private int companyId;

    @Column(name ="max_salary")
    private BigDecimal maxSalary;

    @Column(name ="min_salary")
    private BigDecimal minSalary;

    @Column(name ="phone_number")
    private String phoneNumber;

    @Column(name ="email")
    private String email;

    @Column(name ="content", columnDefinition="TEXT")
    private String content;

    @Column(name ="images", columnDefinition="TEXT")
    private String images;

    @Column(name ="experience")
    private String experience;

    @Column(name ="location_id")
    private int locationId;

    @Column(name ="level_id")
    private int levelId;

    @Column(name ="languages_id")
    private int languagesId;

    @OneToMany
    @JoinTable(
        name = "post_category",
        joinColumns = @JoinColumn(name = "post_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;
    // constructor and other methods...
    public Post( int CompanyId ,BigDecimal maxSalary, BigDecimal minSalary, String phoneNumber, String email, String content, String images, String experience, int locationId, int levelId, int languagesId) {
        this.companyId = CompanyId;
        this.maxSalary = maxSalary;
        this.minSalary = minSalary;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.content = content;
        this.images = images;
        this.experience = experience;
        this.locationId = locationId;
        this.levelId = levelId;
        this.languagesId = languagesId;
    }
}