package com.backend.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
            postDto.getExperience()
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
    public Post getPostById(int id) {
       return postRepository.findPostById(id);
    }
}