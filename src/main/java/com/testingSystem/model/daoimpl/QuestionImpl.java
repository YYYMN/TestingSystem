package com.testingSystem.model.daoimpl;

import com.testingSystem.model.dao.QuestionDao;
import com.testingSystem.model.entity.Question;
import com.testingSystem.model.mapper.QuestionMapper;
import com.testingSystem.spring.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestionImpl implements QuestionDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public QuestionImpl(AppConfig config) {
        jdbcTemplate = new JdbcTemplate(config.dataSource());
    }

    public List<Question> getAllQuestionsByTestId(Integer testId) {
        String SQL_GET_ALL_QUESTIONS_BY_TESTID = "select * from testingsystem.question where testId = ?";
        return jdbcTemplate.query(SQL_GET_ALL_QUESTIONS_BY_TESTID,new Object[] {testId}, new QuestionMapper());
    }

    public List<Question> getAllQuestions() {
        String SQL_GET_ALL_QUESTIONS = "select * from testingsystem.question";
        return jdbcTemplate.query(SQL_GET_ALL_QUESTIONS,new QuestionMapper());
    }
}
