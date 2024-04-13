package com.backend.service.Impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.dto.PostDto;
import com.backend.entity.Post;
import com.backend.entity.User;
import com.backend.repository.PostRepository;
import com.backend.service.PostService;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository PostRepository ;
    @Override
    public void createPost(PostDto postDto) {
        LocalDateTime Date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String creationDate = Date.format(formatter);
        Post post = new Post(postDto.getCompany_id(),
                postDto.getMaxSalary(),
                postDto.getMinSalary(),
                postDto.getPhone_number(),
                postDto.getEmail(),
                postDto.getContent(),
                postDto.getImages(),
                postDto.getExperience(),
                postDto.getLocation_id(),
                postDto.getLevel_id(),
                postDto.getLanguages_id()
        );
    }

    @Override
    public void deletePost(Long id) {
        // TODO Auto-generated method stub 

    }

    @Override
    public List<Post> getAllPosts() {
        return PostRepository.getAllPost();
    }  
}
