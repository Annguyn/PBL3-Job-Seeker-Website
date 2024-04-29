package com.backend.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "full_name",nullable = false)
    private String UserDisplayName;


    @Column(name = "password",nullable = false)
    private String Password;

    @OneToMany(mappedBy = "user")
    private List<Application> applications;

    @Column(name = "role",nullable = false)
    private String Role;

    @OneToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToMany(mappedBy = "applicants")
    private List<Post> appliedPosts;

    public User(String Email, String userDisplayName,  String password,  String role) {
        email = Email;
        UserDisplayName = userDisplayName;
        Password = password;
        Role = role;
    }
}
