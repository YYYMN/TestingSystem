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

    @Autowired
    public TestImpl(AppConfig config) {
        jdbcTemplate = new JdbcTemplate(config.dataSource());
    }

    public List<Test> getAllTests() {
        String SQL_GET_ALL_TESTS = "select * from testingsystem.test";
        return jdbcTemplate.query(SQL_GET_ALL_TESTS, new TestMapper());
    }

    public List<Test> getAllTestsByTopicId(int topicId) {
        String SQL_GET_ALL_TESTS = "select * from testingsystem.test where topicId = '"+topicId+"'";
        return jdbcTemplate.query(SQL_GET_ALL_TESTS, new TestMapper());
    }

    @Override
    public Test getTestByDescription(String test) {
        String SQL_GET_TEST_BY_DESCRIPTION = "select * from testingsystem.test where description ='"+test+"'";
        return jdbcTemplate.queryForObject(SQL_GET_TEST_BY_DESCRIPTION, new TestMapper());
    }
}
