package com.testingSystem.model.daoimpl;


import com.testingSystem.model.dao.UserDao;
import com.testingSystem.model.entity.User;
import com.testingSystem.model.mapper.UserMapper;
import com.testingSystem.spring.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

    public User getUserById(Integer userId) {

        String SQL_GET_USER = "select * from testingsystem.user where userId = ?";
        return jdbcTemplate.queryForObject(SQL_GET_USER,new UserMapper(), userId);
    }

    public User getUserByLogin(String login) {

        User user;
        String SQL_GET_USER_BY_LOGIN = "select * from testingsystem.user where login = ?";
        try {
          user = jdbcTemplate.queryForObject(SQL_GET_USER_BY_LOGIN,new UserMapper(),login);
        }catch (EmptyResultDataAccessException e){
            user = null;
        }
        return user;
    }



    @Override
    public void updateUser(User user) {
        String SQL_UPDATE_USER = "UPDATE testingsystem.user set user.lastName = ?, user.firstName = ?," +
                " user.login = ?, user.password = ?, user.email = ?, user.roleId = ? " +
                "  WHERE user.userId = ?";

        jdbcTemplate.update(SQL_UPDATE_USER,
                user.getLastName(),user.getFirstName(),user.getLogin(),
                user.getPassword(),user.getEmail(),user.getRoleId(),
                user.getUserId());
    }

    @Override
    public void deleteUserById(Integer userId) {
        String SQL_DELETE_USER = "delete from testingsystem.user where userId = ?";
        String SQL_DELETE_USER_STATISTIC = "delete FROM testingsystem.statistic where userId = ?";
        jdbcTemplate.update(SQL_DELETE_USER_STATISTIC, userId);
        jdbcTemplate.update(SQL_DELETE_USER,userId);
    }
}
