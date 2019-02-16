package com.testingSystem.model.daoimpl;


import com.testingSystem.model.dao.UserDao;
import com.testingSystem.model.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserImpl implements UserDao {

    public List<User> getAllUsers() {
        return null;
    }

    public void addUser(User user) {

    }

    public User getUserById(Integer id) {
        return null;
    }
}
