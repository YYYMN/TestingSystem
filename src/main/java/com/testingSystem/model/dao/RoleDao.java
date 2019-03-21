package com.testingSystem.model.dao;

import com.testingSystem.model.entity.Role;

public interface RoleDao {

/*    *//**
     * @return вернёт список ролей в виде String объектов.
     * Т.е названия ролей. В БД они хранятся в формате "a,b,c" например (1,0,0)
     *//*
    List<String> getRolesStringList();*/

    /**
     * @param userRoles по названию роли сбегает в БД и узнает её Id. Смотри сущьность Role,
     * там есть статический метод getRoleData(...), который будет применяться в этой ф-ции.
     * @return Id роли.
     */
    Role getRoleByUserRoles(String[] userRoles);

    Role getRoleByRoleId(Integer roleId);
}
