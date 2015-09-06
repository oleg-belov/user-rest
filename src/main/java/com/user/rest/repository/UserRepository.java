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

    public int insert(User user) {
        return jdbc.update("INSERT INTO sc_user VALUES(?,?)", counter.incrementAndGet(),user.getName());
    }

    private static final RowMapper<User> userMapper = new RowMapper<User>() {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User(rs.getString("name"),rs.getLong("id"));
            return user;
        }
    };


}
