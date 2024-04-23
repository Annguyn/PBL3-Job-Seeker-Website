package com.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.entity.Category;
import com.backend.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{
    @Query(
            value = "select * from recruitment_post",
            nativeQuery = true)
    List<Post> getAllPost();

    List<Post> findAllByOrderByDatePostedDesc();
    List<Category> getCategoryById(@Param("id") Integer id);
    List<Post> findAllByOrderByMaxSalaryDesc();
    List<Post> findAllByOrderByMaxSalaryAsc();
    Post findPostById(int id);
}