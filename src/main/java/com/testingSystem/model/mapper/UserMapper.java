package com.testingSystem.model.mapper;

import com.testingSystem.model.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setName(resultSet.getString("firstName"));
        user.setSurName(resultSet.getString("lastName"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setUserId(resultSet.getInt("userId"));
        user.setRole(resultSet.getString("roleId"));
        return user;
    }
}
