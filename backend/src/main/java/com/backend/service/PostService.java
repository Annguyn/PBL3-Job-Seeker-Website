package com.backend.service;

import java.util.List;

import com.backend.entity.Application;
import com.backend.entity.Company;
import com.backend.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.backend.dto.PostDto;
import com.backend.entity.Post;

@Service
public interface PostService {

    List<Post> getAllPosts();
    void deletePost(Long id);
    List<Post> getAllPostsOrderByDatetime();
    Page<Post> getAllPosts(Pageable pageable) ;
    List<Post> getAllPostsOrderBySalaryDes();
    List<Post> getAllPostsOrderBySalaryAsc();
    Post getPostById(int id);
    List<Post> getPostByCompany(Company company);
    void save(Post post); 
}
