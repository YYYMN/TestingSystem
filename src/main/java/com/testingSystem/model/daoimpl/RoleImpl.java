package com.testingSystem.model.daoimpl;

import com.testingSystem.model.dao.RoleDao;
import com.testingSystem.model.entity.Role;
import com.testingSystem.model.mapper.RoleMapper;
import com.testingSystem.spring.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleImpl implements RoleDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public RoleImpl(AppConfig config) {
        jdbcTemplate = new JdbcTemplate(config.dataSource());
    }

    @Override
    public List<Role> getAllRoles() {
        String SQL_GET_ALL_ROLES = "select * from testingsystem.role";
        return jdbcTemplate.query(SQL_GET_ALL_ROLES,new RoleMapper());
    }
}
