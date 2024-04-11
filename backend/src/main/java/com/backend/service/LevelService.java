package com.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.entity.Level;
import com.backend.repository.LevelRepository;

@Service
public class LevelService {

    @Autowired
    private LevelRepository levelRepository;

    public List<Level> getAllLevel() {
        return levelRepository.getAllLevel();
    }
}
