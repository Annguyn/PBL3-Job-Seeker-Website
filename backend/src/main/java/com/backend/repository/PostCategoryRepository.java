package com.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.backend.entity.Category;
import com.backend.entity.Post;
import com.backend.entity.PostCategory;

@Repository
@EnableJpaRepositories
public interface PostCategoryRepository extends JpaRepository<PostCategoryRepository,Integer>{
    @Query(
        value = "select * from post_category",
        nativeQuery = true)
    List<PostCategoryRepository> getAllPostCategory();

    @Query(
    value = "select * from post_category where post_id = ?1",
    nativeQuery = true)
List<Category> getCategoriesOfAnPost(int id );
void save(PostCategory postCategory); 

@Query(
    value = "select * from post_category where post_id = ?1 and category_id = ?2",
    nativeQuery = true)
void delete(int post_id, int category_id);
}