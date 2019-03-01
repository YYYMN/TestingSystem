package com.testingSystem.model.mapper;

import com.testingSystem.model.entity.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Role role = new Role();

        int user = resultSet.getInt("user");
        int tutor = resultSet.getInt("tutor");
        int admin = resultSet.getInt("admin");
        if (user == 1 & tutor == 0 & admin == 0 ){
            role.setRole("User");
            role.setRoleId(resultSet.getInt("roleId"));
        }
        else if (user == 0 & tutor == 1 & admin == 0 ){
            role.setRole("Tutor");
            role.setRoleId(resultSet.getInt("roleId"));
        }
        else if (user == 0 & tutor == 0 & admin == 1 ){
            role.setRole("Admin");
            role.setRoleId(resultSet.getInt("roleId"));
        }
        return role;
    }
}
