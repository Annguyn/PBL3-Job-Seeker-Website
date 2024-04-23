package com.backend.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.backend.dto.PostDto;
import com.backend.entity.Post;

@Service
public interface PostService {
    void createPost(PostDto postDto);
    
    // Post getPostById(Long id);
    List<Post> getAllPosts();
    void deletePost(Long id);
    List<Post> getAllPostsOrderByDatetime();
    Page<Post> getAllPosts(Pageable pageable) ;
    // List<Category> getCategoryByPostId(int id);
    List<Post> getAllPostsOrderBySalaryDes();

    List<Post> getAllPostsOrderBySalaryAsc();
    Post getPostById(int id);
    void save(Post post); 
}
