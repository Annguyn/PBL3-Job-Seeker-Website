package com.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend.entity.Location;
import com.backend.entity.User;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    // Không cần phải viết các phương thức CRUD, chúng được cung cấp sẵn bởi JpaRepository
    @Query(
            value = "select * from job_location",
            nativeQuery = true)
    List<Location> getAllLocation();
}
