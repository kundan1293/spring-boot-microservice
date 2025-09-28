package com.example.springbootmicroservice.repository;

import com.example.springbootmicroservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<User> userRowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            return user;
        }
    };

    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM user", userRowMapper);
    }

    public User findById(Long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM user WHERE id = ?", userRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int save(User user) {
        return jdbcTemplate.update("INSERT INTO user (name, email) VALUES (?, ?)", user.getName(), user.getEmail());
    }

    public int update(User user) {
        return jdbcTemplate.update("UPDATE user SET name = ?, email = ? WHERE id = ?", user.getName(), user.getEmail(),
                user.getId());
    }

    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM user WHERE id = ?", id);
    }
}