package com.testingSystem.model.mapper;

import com.testingSystem.model.entity.Question;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionMapper implements RowMapper<Question> {

    @Override
    public Question mapRow(ResultSet resultSet, int i) throws SQLException {
        Question question = new Question();
        question.setQuestionId(resultSet.getInt("questionId"));
        question.setDescription(resultSet.getString("description"));
        return question;
    }
}
