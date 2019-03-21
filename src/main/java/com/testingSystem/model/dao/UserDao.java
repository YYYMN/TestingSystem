package com.testingSystem.model.dao;


import java.util.List;
import com.testingSystem.model.entity.User;

public interface UserDao {
    List<User> getAllUsers();
    void addUser(User user);
    User getUserByUserId(Integer userId);
    void updateUser(User user);
    void deleteUserByUserId(Integer userId);
}
