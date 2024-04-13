package com.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{
    @Query(
            value = "select * from recruitment_post",
            nativeQuery = true)
    List<Post> getAllPost();
}
