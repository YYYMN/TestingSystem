package com.testingSystem.model.mapper;

import com.testingSystem.model.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getInt("userId"));
        user.setLastName(resultSet.getString("lastName"));
        user.setFirstName(resultSet.getString("firstName"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));
        user.setRole(resultSet.getInt("roleId"));
        return user;
    }
}
