package com.backend.service;

import org.springframework.stereotype.Service;

import com.backend.entity.Profile;

@Service
public interface ProfileService {
    void update(Profile profile);
}
