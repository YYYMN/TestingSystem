package com.testingSystem.model.mapper;

import com.testingSystem.model.entity.Role;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper implements RowMapper<Role> {

    @Override
    public Role mapRow(ResultSet resultSet, int i) throws SQLException {
        Role role = new Role();
        role.setRoleId(resultSet.getInt("roleId"));
        role.setAdmin(resultSet.getInt("admin"));
        role.setTutor(resultSet.getInt("tutor"));
        role.setUser(resultSet.getInt("user"));
        return role;
    }
}
