package com.backend.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDTO implements Serializable {
    private Long id;
    private Long userId;
    private Long postId;
    private String fullName;
    private String emailAddress;
    private String phoneNumber;
    private String currentPreviousJobTitle;
    private String linkedinUrl;
    private String githubUrl;
    private String additionalInformation;
    private byte[] resume;

}