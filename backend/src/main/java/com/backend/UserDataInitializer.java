package com.backend;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserDataInitializer {

    private final JdbcTemplate jdbcTemplate;

    public UserDataInitializer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertUserData() {
        jdbcTemplate.execute("INSERT INTO Users (Username, Password, Email, Role) VALUES " +
                "('admin', 'admin123', 'admin@example.com', 'admin'), " +
                "('user1', 'user123', 'user1@example.com', 'user'), " +
                "('user2', 'user456', 'user2@example.com', 'user')");
    }
}
