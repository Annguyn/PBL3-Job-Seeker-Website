package com.backend.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.dto.PostDto;
import com.backend.entity.Category;
import com.backend.entity.Post;
import com.backend.repository.PostRepository;
import com.backend.service.PostService;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
public void createPost(PostDto postDto) {
    Post post = new Post(
            postDto.getCompanyId(),
            postDto.getMaxSalary(),
            postDto.getMinSalary(),
            postDto.getPhoneNumber(),
            postDto.getEmail(),
            postDto.getContent(),
            postDto.getImages(),
            postDto.getExperience(),
            postDto.getLocationId(),
            postDto.getLevelId(),
            postDto.getLanguagesId()
    );
    postRepository.save(post);
}

    // @Override
    // public void deletePost(Long id) {
    //     postRepository.deleteById(id);
    // }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.getAllPost();
    }

    // @Override
    // public List<Category> getCategoryByPostId(int id) {
    //     return postRepository.getCategoryByPostId(id);
    // }

    @Override
    public void deletePost(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletePost'");
    }  
}