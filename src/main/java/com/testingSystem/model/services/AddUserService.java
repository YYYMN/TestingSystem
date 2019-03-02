package com.testingSystem.model.services;

import com.testingSystem.model.dao.RoleDao;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddUserService {

    private RoleDao roleDao;

    public AddUserService(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public void addUser(){

    }

    public List<String> rolesList(){
        return roleDao.getRolesStringList();
    }

}
