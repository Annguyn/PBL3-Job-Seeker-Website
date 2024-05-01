package com.backend.repository;

import java.util.List;

import com.backend.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface PostRepository extends JpaRepository<Post, Integer>{
    @Query(
            value = "select * from recruitment_post",
            nativeQuery = true)
    List<Post> getAllPost();
    List<Post> findAllByCompany(Company company);
    List<Post> findAllByOrderByDatePostedDesc();
    List<Category> getCategoryById(@Param("id") Integer id);
    List<Post> findAllByOrderByMaxSalaryDesc();
    List<Post> findAllByOrderByMaxSalaryAsc();
    Post findPostById(int id);
    @Query(
            value = "select u.* from user u inner join applications a on u.id = a.user_id where a.post_id = :id",
            nativeQuery = true)
    List<User> getUserApplicantsByPostId(int id);

}