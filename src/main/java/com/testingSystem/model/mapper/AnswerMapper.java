package com.testingSystem.model.mapper;

import com.testingSystem.model.entity.Answer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnswerMapper implements RowMapper<Answer> {

    @Override
    public Answer mapRow(ResultSet resultSet, int i) throws SQLException {
        Answer answer = new Answer();
        answer.setDescription(resultSet.getString("description"));
        answer.setCorrect(resultSet.getInt("correct"));
        answer.setQuestionId(resultSet.getInt("questionId"));
        return answer;
    }
}
