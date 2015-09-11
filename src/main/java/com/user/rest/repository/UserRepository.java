package com.user.rest.repository;

import com.user.rest.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sergey on 06.09.15.
 */
@Repository
public class UserRepository {

    private final AtomicInteger counter = new AtomicInteger(1);

    @Autowired
    protected JdbcTemplate jdbc;

    public User getUser(long id) {
        return jdbc.queryForObject("SELECT * FROM sc_user WHERE id=?", userMapper, id);
    }

    public List<User> getUsers() {
        return jdbc.query("SELECT * FROM sc_user", userMapper);
    }

    public int insert(final User user) {
        int id = counter.incrementAndGet();
        jdbc.update("INSERT INTO sc_user VALUES(?,?,?,?,?)", id, user.getUsername(), user.getFirstname(), user.getLastname(), user.getEmail());
        return id;
    }

    public int update(long id, User user) {
        return jdbc.update("UPDATE sc_user SET user_name=?,first_name=?,last_name=?,email=? WHERE id=?", user.getUsername(), user.getFirstname(), user.getLastname(), user.getEmail(), id);
    }

    public int delete(long id) {
        return jdbc.update("DELETE FROM sc_user WHERE id=?", id);
    }

    private static final RowMapper<User> userMapper = new RowMapper<User>() {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setUid(rs.getLong("id"));
            user.setUsername(rs.getString("user_name"));
            user.setFirstname(rs.getString("first_name"));
            user.setLastname(rs.getString("last_name"));
            user.setEmail(rs.getString("email"));
            return user;
        }
    };

}
