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
    private QuestionImpl questionImpl;

    @Autowired
    public QuestionImpl(AppConfig config) {
        jdbcTemplate = new JdbcTemplate(config.dataSource());
    }

    public List<Question> getAllQuestionsByTestId(Integer testId) {
        String SQL_GET_ALL_QUESTIONS_BY_TESTID = "select * from testingsystem.question where testId = ?";
        return jdbcTemplate.query(SQL_GET_ALL_QUESTIONS_BY_TESTID,new Object[] {testId}, new QuestionMapper());
    }

    public void addQuestionToDb(String question) {
        String SQL_CHECK_IF_QUESTION_EXIST = "SELECT Count(questionId) FROM question WHERE description='"+question+"'";
        int exist = jdbcTemplate.queryForObject(SQL_CHECK_IF_QUESTION_EXIST, Integer.class);
        if (exist == 0){
            String SQL_ADD_QUESTION_TO_DB = "insert into question (description) values(?)";
            jdbcTemplate.update(SQL_ADD_QUESTION_TO_DB, question);
        }


    }

    @Override
    public Question getQuestionByDescription(String question) {
        String SQL_GET_QUESTION_BY_DESCRIPTION = "select * from question where question.description='"+question+"'";
        return jdbcTemplate.queryForObject(SQL_GET_QUESTION_BY_DESCRIPTION, new QuestionMapper());
    }

    public List<Question> getAllQuestions() {
        String SQL_GET_ALL_QUESTIONS = "select * from testingsystem.question";
        return jdbcTemplate.query(SQL_GET_ALL_QUESTIONS, new QuestionMapper());
    }
}
