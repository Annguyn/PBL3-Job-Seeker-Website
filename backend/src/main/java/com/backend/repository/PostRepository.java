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
    // @Query(value = "SELECT c.id, c.name, c.quantity FROM category c JOIN post_category pc ON pc.category_id = c.id WHERE pc.post_id = :id", nativeQuery = true)
    // List<Category> getCategoryByPostId(@Param("id") Integer id);
    List<Post> findAllByOrderByMaxSalaryDesc();

    List<Post> findAllByOrderByMaxSalaryAsc();
}