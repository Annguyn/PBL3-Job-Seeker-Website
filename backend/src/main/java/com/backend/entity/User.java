package com.backend.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    //Tài khoản đăng nhập kết nối với database
    @Id
    @Column(name = "UserID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "Email",nullable = false)
    private String email;

    @Column(name = "Username",nullable = false)
    private String UserDisplayName;

    // @Column(name = "about_me",nullable = true)
    // private String AboutMe;

    // @Column(name = "views",nullable = false)
    // private int Views;

    // @Column(name = "topic_counts",nullable = false)
    // private int TopicCounts;

    @Column(name = "Password",nullable = false)
    private String Password;

    // @Column(name = "creation_date",nullable = false)
    // private String CreationDate;

    @Column(name = "Role",nullable = false)
    private String Role;


    public User(String Email, String userDisplayName,  String password,  String role) {
        email = Email;
        UserDisplayName = userDisplayName;
        // AboutMe = aboutMe;
        // Views = views;
        // TopicCounts = topicCounts;
        Password = password;
        // CreationDate = creationDate;
        Role = role;
    }
}
