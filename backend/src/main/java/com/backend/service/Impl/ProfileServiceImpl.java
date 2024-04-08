package com.backend.service.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.entity.Profile;
import com.backend.reponsitory.ProfileReponsitory;
import com.backend.service.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileReponsitory profileReponsitory;

    @Override
    public void update(Profile profile) {
        profileReponsitory.save(profile);
    }
}
