package com.testingSystem.model.daoimpl;


import com.testingSystem.model.dao.UserDao;
import com.testingSystem.model.entity.User;
import com.testingSystem.model.mapper.UserMapper;
import com.testingSystem.spring.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserImpl implements UserDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserImpl(AppConfig config) {
        jdbcTemplate = new JdbcTemplate(config.dataSource());
    }

    public List<User> getAllUsers() {
        String SQL_GET_ALL_USERS = "select * from testingsystem.user";
        return jdbcTemplate.query(SQL_GET_ALL_USERS, new UserMapper());
    }

    public void addUser(User user) {
        String SQL_ADD_USER = "insert into testingsystem.user (lastName, firstName, login, password, email, roleId)" +
                "values (?,?,?,?,?,?)";
        jdbcTemplate.update(SQL_ADD_USER, user.getLastName(),
                user.getFirstName(),
                user.getLogin(),
                user.getPassword(),
                user.getEmail(),
                user.getRoleId());
    }

    public User getUserById(Integer id) {
        return null;
    }
}
