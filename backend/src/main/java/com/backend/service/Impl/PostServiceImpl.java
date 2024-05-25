package com.backend.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import com.backend.repository.LocationRepository;
import com.backend.entity.*;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.backend.repository.PostRepository;
import com.backend.service.PostService;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<Post> getAllPosts() {
        return postRepository.getAllPost();
    }
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
    public List<Post> getPostByCompany(Company company) {
        return postRepository.findAllByCompany(company);
    }

    @Override
    public Post getPostById(int id) {
       return postRepository.findPostById(id);
    }

    @Override
    public void save(Post post) {
        postRepository.save(post);
    }

    @Override
    public void delete(Post post) {
        postRepository.delete(post);
        for (Application application : post.getApplications()) {
            application.setPost(null);
        }
    }
    @Override
    public Page<Post> getAllPosts(Pageable pageable, List<String> filterCategory, List<String> filterLevel, List<String> filterSalary, String keySearch, String location) {
        return postRepository.findAll((root, query, cb) -> {
            List<jakarta.persistence.criteria.Predicate> predicates = new ArrayList<>();

            if (keySearch != null && !keySearch.isEmpty()) {
                predicates.add(cb.like(root.get("content"), "%" + keySearch + "%"));
            }

            if (filterCategory != null && !filterCategory.isEmpty()) {
                // Create a disjunction (OR) condition for multiple categories
                Predicate[] categoryPredicates = filterCategory.stream()
                        .map(category -> cb.equal(root.join("categories").get("name"), category))
                        .toArray(Predicate[]::new);
                predicates.add(cb.or(categoryPredicates));
            }

            if (filterLevel != null && !filterLevel.isEmpty()) {
                Predicate[] levelPredicates = filterLevel.stream()
                        .map(level -> cb.equal(root.get("level").get("name"), level))
                        .toArray(Predicate[]::new);
                predicates.add(cb.or(levelPredicates));
            }

            if (filterSalary != null && !filterSalary.isEmpty()) {
                for (String salaryRange : filterSalary) {
                    if (salaryRange.contains("or above")) {
                        String minSalaryStr = salaryRange.replace("M", "").replace(" or above", "").trim();
                        BigDecimal minSalary = new BigDecimal(minSalaryStr).multiply(BigDecimal.valueOf(1000000));
                        predicates.add(cb.greaterThanOrEqualTo(root.get("maxSalary"), minSalary));
                    } else {
                        String[] parts = salaryRange.replace("M", "").split("->");
                        BigDecimal minSalary = new BigDecimal(parts[0].trim()).multiply(BigDecimal.valueOf(1000000));
                        BigDecimal maxSalary = new BigDecimal(parts[1].trim()).multiply(BigDecimal.valueOf(1000000));
                        predicates.add(cb.between(root.get("maxSalary"), minSalary, maxSalary));
                    }
                }
            }

            if (location != null && !location.isEmpty()) {
                predicates.add(cb.equal(root.get("location").get("name"), location));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        }, pageable);
    }
    @Override
    public List<String> getPostDetailsContaining(String query) {
        return postRepository.findPostDetailsByQuery(query);
    }
}