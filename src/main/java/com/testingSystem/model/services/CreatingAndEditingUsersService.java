package com.testingSystem.model.services;

import com.testingSystem.model.dao.RoleDao;
import com.testingSystem.model.dao.UserDao;
import com.testingSystem.model.entity.Role;
import com.testingSystem.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreatingAndEditingUsersService {

    private RoleDao roleDao;
    private UserDao userDao;

    @Autowired
    public CreatingAndEditingUsersService(RoleDao roleDao, UserDao userDao) {
        this.roleDao = roleDao;
        this.userDao = userDao;
    }

    public void CreatingUser(User user){
            Role role = roleDao.getRoleByUserRoles(user.getRoles());
            user.setRoleId(role.getRoleId());
            userDao.addUser(user);
    }

    public User getUserById(Integer userId){
        User user = userDao.getUserById(userId);
        Role role = roleDao.getRoleByRoleId(user.getRoleId());
        user.setRoles(role.getRoles());
        return user;
    }

    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }

    public void updateUser(User user){
        Role role = getRoleByUserRoles(user.getRoles());
        user.setRoleId(role.getRoleId());
        userDao.updateUser(user);
    }

    public void deleteUserByUserId(Integer userId) { userDao.deleteUserById(userId);}

    /**
     *
     * @return вернёт массив всех возможных ролей,
     * которые можно присвоись пользователю, а именно
     * {"Admin","Tutor","User"}
     */
    public String[] allRolesList(){
        return Role.allRoles;
    }

    public Role getRoleByUserRoles(String[] userRoles){
        return roleDao.getRoleByUserRoles(userRoles);
    }

}
