package com.backend.service.Impl;

import java.util.List;

import com.backend.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.backend.dto.PostDto;
import com.backend.repository.PostRepository;
import com.backend.service.PostService;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> getAllPosts() {
        return postRepository.getAllPost();
    }
    @Override
    public List<Post> getAllPostsOrderByDatetime() {
        return postRepository.findAllByOrderByDatePostedDesc();
    }
    @Override
    public void deletePost(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletePost'");
    }  

    @Override
    public Page<Post> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public List<Post> getAllPostsOrderBySalaryDes() {
        return postRepository.findAllByOrderByMaxSalaryDesc();
    }


    @Override
    public List<Post> getAllPostsOrderBySalaryAsc() {
       return postRepository.findAllByOrderByMaxSalaryAsc();
    }
    @Override
    public List<Post> getPostByCompany(Company company) {
        return postRepository.findAllByCompany(company);
    }

    @Override
    public Post getPostById(int id) {
       return postRepository.findPostById(id);
    }

    @Override
    public void save(Post post) {
        postRepository.save(post);
    }

    @Override
    public void delete(Post post) {
        postRepository.delete(post);
        for (Application application : post.getApplications()) {
            application.setPost(null);
        }
    }
}