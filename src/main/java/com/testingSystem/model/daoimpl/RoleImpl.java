package com.testingSystem.model.daoimpl;

import com.testingSystem.model.dao.RoleDao;
import com.testingSystem.model.entity.Role;
import com.testingSystem.model.mapper.RoleMapper;
import com.testingSystem.spring.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleImpl implements RoleDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public RoleImpl(AppConfig config) {
        jdbcTemplate = new JdbcTemplate(config.dataSource());
    }

/*    @Override
    public List<String> getRolesStringList(Role role) {
        String SQL_GET_ALL_ROLES = "select * from testingsystem.role";
        List<Role> roleList = jdbcTemplate.query(SQL_GET_ALL_ROLES,new RoleMapper());
        List<String> rolesStringList = new ArrayList<>();
        roleList.forEach(role -> rolesStringList.add(Role.getRolesList()));
        return rolesStringList;
    }*/

    @Override
    public Role getRoleByUserRoles(String[] userRoles){
        String SQL_GET_ROLE_ID = "select * from testingsystem.role where admin = ? and tutor = ? and user = ?";
        return jdbcTemplate.queryForObject(SQL_GET_ROLE_ID, new RoleMapper(), Role.getRoleData(userRoles));
    }

    @Override
    public Role getRoleByRoleId(Integer roleId) {
        String SQL_GET_ROLE_ID = "select * from testingsystem.role where roleId = ?";
        return jdbcTemplate.queryForObject(SQL_GET_ROLE_ID, new RoleMapper(), roleId);
    }
}
