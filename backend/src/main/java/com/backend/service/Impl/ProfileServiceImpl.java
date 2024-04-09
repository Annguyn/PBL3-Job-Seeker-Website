package com.backend.service.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.entity.Profile;
import com.backend.repository.ProfileRepository;
import com.backend.service.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public void update(Profile profile) {
        profileRepository.save(profile);
    }
}
