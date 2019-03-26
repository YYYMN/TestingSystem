package com.testingSystem.model.dao;


import java.util.List;
import com.testingSystem.model.entity.User;

public interface UserDao {
    List<User> getAllUsers();
    User getUserById(Integer userId);
    User getUserByLogin(String login);
    void addUser(User user);
    void updateUser(User user);
    void deleteUserById(Integer userId);
}
