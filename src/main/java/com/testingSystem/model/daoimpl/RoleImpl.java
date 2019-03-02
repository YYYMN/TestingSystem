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

    @Override
    public List<String> getRolesStringList() {
        String SQL_GET_ALL_ROLES = "select * from testingsystem.role";
        List<Role> roleList = jdbcTemplate.query(SQL_GET_ALL_ROLES,new RoleMapper());
        List<String> rolesStringList = new ArrayList<>();
        roleList.forEach(role -> rolesStringList.add(Role.getRole(role)));
        return rolesStringList;
    }

    @Override
    public Integer getRoleId(String role){
        String SQL_GET_ROLE_ID = "select roleId from testingsystem.role where admin = ? and tutor = ? and user = ?";
        return jdbcTemplate.queryForObject(SQL_GET_ROLE_ID,Integer.class,Role.getRoleData(role));
    }
}
