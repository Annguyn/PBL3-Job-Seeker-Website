package com.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.dto.PostDto;
import com.backend.entity.Category;
import com.backend.entity.Post;

@Service
public interface PostService {
    void createPost(PostDto postDto);
    // Post getPostById(Long id);
    List<Post> getAllPosts();
    void deletePost(Long id);
    // List<Category> getCategoryByPostId(int id);
}
