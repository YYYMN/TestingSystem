package com.testingSystem.model.daoimpl;

import com.testingSystem.model.dao.TestDao;
import com.testingSystem.model.entity.Test;
import com.testingSystem.model.mapper.TestMapper;
import com.testingSystem.spring.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class TestImpl implements TestDao {

    private JdbcTemplate jdbcTemplate;

    private final String SQL_GET_ALL_TESTS= "select * from testingsystem.test";

    @Autowired
    public TestImpl(AppConfig config) {
        jdbcTemplate = new JdbcTemplate(config.dataSource());
    }

    public List<Test> getAllTests() {
        return jdbcTemplate.query(SQL_GET_ALL_TESTS, new TestMapper());

    }

    }
