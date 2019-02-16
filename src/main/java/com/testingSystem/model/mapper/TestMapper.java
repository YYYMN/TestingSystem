package com.testingSystem.model.mapper;

import com.testingSystem.model.entity.Test;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class TestMapper implements RowMapper<Test> {

    public Test mapRow(ResultSet resultSet, int i) throws SQLException {
        Test test = new Test();
        test.setTestName(resultSet.getString("name"));
        test.setDescription(resultSet.getString("description"));
        test.setTestId(resultSet.getInt("testId"));
        test.setTopicId(resultSet.getInt("topicId"));
        return test;
    }
}
