package com.testingSystem.model.services;

import com.testingSystem.model.dao.RoleDao;
import com.testingSystem.model.dao.UserDao;
import com.testingSystem.model.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddUserService {

    private RoleDao roleDao;
    private UserDao userDao;

    public AddUserService(RoleDao roleDao, UserDao userDao) {
        this.roleDao = roleDao;
        this.userDao = userDao;
    }

    public void addUser(String role, String lastName, String firstName,
                        String password, String login, String email){
            Integer roleId = roleDao.getRoleId(role);
            userDao.addUser(new User(lastName,firstName,login,password,email,roleId));
    }

    public List<String> rolesList(){
        return roleDao.getRolesStringList();
    }

}
