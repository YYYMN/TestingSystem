package com.testingSystem.model.dao;


import com.sun.tools.javac.util.List;
import com.testingSystem.model.entity.User;

public interface UserDao {
    List<User> getAllUsers();
    void addUser(User user);
    User getUserById(Integer id);
}
