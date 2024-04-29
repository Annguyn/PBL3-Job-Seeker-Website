package com.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "applications")
@NoArgsConstructor
@AllArgsConstructor
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "post_id")
    private int postId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id", insertable = false, updatable = false)
    private Post post;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "current_previous_job_title")
    private String currentPreviousJobTitle;

    @Column(name = "linkedin_url")
    private String linkedinUrl;

    @Column(name = "github_url")
    private String githubUrl;

    @Column(name = "additional_information")
    private String additionalInformation;

    @Lob
    @Column(name = "resume")
    private byte[] resume;

    public Application(User user , Post post, String fullName, String emailAddress, String phoneNumber, String currentPreviousJobTitle, String linkedinUrl, String githubUrl, String additionalInformation, byte[] resume) {
        this.user = user;
        this.post = post;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.currentPreviousJobTitle = currentPreviousJobTitle;
        this.linkedinUrl = linkedinUrl;
        this.githubUrl = githubUrl;
        this.additionalInformation = additionalInformation;
        this.resume = resume;
        this.userId = user.getID();
        this.postId= post.getId();
    }
}