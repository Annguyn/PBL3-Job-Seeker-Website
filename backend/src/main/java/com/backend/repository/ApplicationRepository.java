package com.backend.repository;

import com.backend.entity.Application;
import com.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    List<Application> findByPostId(int id);
    Application findById(int id) ;
    List<Application> findAllByUser(User user);
    @Query("SELECT a FROM Application a WHERE a.post IN (SELECT p FROM Post p WHERE p.company.id = :companyId)")
    List<Application> findAllByPostCompany(@Param("companyId") int companyId);


}