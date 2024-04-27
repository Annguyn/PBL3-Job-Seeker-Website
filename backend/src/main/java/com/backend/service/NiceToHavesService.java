package com.backend.service;

import com.backend.entity.NiceToHaves;
import com.backend.repository.NiceToHavesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NiceToHavesService {
    public List<NiceToHaves> getAllNiceToHaves() ;
    public NiceToHaves getNiceToHavesById(int id);
    public List<NiceToHaves> getNiceToHavesByIds(List<Integer> ids);
}
