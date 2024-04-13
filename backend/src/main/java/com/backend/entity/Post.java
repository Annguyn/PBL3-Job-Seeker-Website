package com.backend.entity;

import org.aspectj.lang.annotation.control.CodeGenerationHint;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private int company_id;

    @Column(name ="max_salary")
    private int maxSalary;

    @Column(name ="min_salary")
    private int minSalary;

    @Column(name ="phone_number")
    private String phone_number;

    @Column(name ="email")
    private String email;

    @Column(name ="content")
    private String content;

    @Column(name ="images")
    private String images;

    @Column(name ="experience")
    private int experience;

    @Column(name ="location_id")
    private int location_id;

    @Column(name ="level_id")
    private int level_id;

    @Column(name ="languages_id")
    private int languages_id;

    public Post(int Company_id, int MaxSalary, int MinSalary, String PhoneNumber, String Email, String Content, String Images, int Experience, int Location_id, int Level_id, int Languages_id){
        company_id = Company_id;
        maxSalary = MaxSalary;
        minSalary = MinSalary;
        phone_number = PhoneNumber;
        email = Email;
        content = Content;
        images = Images;
        experience = Experience;
        location_id = Location_id;
        level_id = Level_id;
        languages_id = Languages_id;
    }
}
