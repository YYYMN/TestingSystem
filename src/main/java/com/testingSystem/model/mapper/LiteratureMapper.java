package com.testingSystem.model.mapper;

import com.testingSystem.model.entity.Literature;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LiteratureMapper implements RowMapper<Literature> {

    @Override
    public Literature mapRow(ResultSet resultSet, int i) throws SQLException {
        Literature literature = new Literature();
        literature.setLiteratureId(resultSet.getInt("literatureId"));
        literature.setDescription(resultSet.getString("description"));
        literature.setQuestionId(resultSet.getInt("questionId"));
        return literature;
    }
}
