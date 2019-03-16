package com.testingSystem.model.services;

import com.testingSystem.model.dao.RoleDao;
import com.testingSystem.model.dao.UserDao;
import com.testingSystem.model.entity.Role;
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

    public void addUser(User user){
            Integer roleId = roleDao.getRoleId(user.getRoles());
            user.setRole(roleId);
            userDao.addUser(user);
    }

    /**
     *
     * @return вернёт массив всех возможных ролей,
     * которые можно присвоись пользователю, а именно
     * {"Admin","Tutor","User"}
     */
    public String[] allRolesList(){
        return Role.allRoles;
    }

}
