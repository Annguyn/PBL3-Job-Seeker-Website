package com.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.backend.entity.Profile;
import com.backend.entity.User;

@Repository
@EnableJpaRepositories
public interface ProfileRepository extends JpaRepository<Profile,Integer> {
    Profile findProfileByUser(User user);
    Profile findProfileById(int id);
}
