package com.testingSystem.model.daoimpl;

import com.testingSystem.model.dao.AnswerDao;
import com.testingSystem.model.entity.Answer;
import com.testingSystem.model.entity.Question;
import com.testingSystem.model.mapper.AnswerMapper;
import com.testingSystem.model.mapper.QuestionMapper;
import com.testingSystem.spring.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class AnswerImpl implements AnswerDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AnswerImpl(AppConfig config) {
        jdbcTemplate = new JdbcTemplate(config.dataSource());
    }

    @Override
    public List<Answer> getAllAnswers() {
        String SQL_GET_ALL_ANSWERS =  "select * from testingsystem.answer";
        return jdbcTemplate.query(SQL_GET_ALL_ANSWERS, new AnswerMapper());
    }

    @Override
    public List<Answer> getAnswersByQuestionId(int questionId) {
        String SQL_GET_ANSWERS_BY_QUESTION_ID =  "select * from testingsystem.answer where answer.questionId = '" +questionId+"'";
        return jdbcTemplate.query(SQL_GET_ANSWERS_BY_QUESTION_ID, new AnswerMapper());
    }

    @Override
    public void deleteAnswerFromDb(String answer) {
        String SQL_DELETE_ANSWER_FROM_DB =  "delete from testingsystem.answer where answer.description = '" +answer+"'";
        jdbcTemplate.update(SQL_DELETE_ANSWER_FROM_DB);
    }

    @Override
    public void addAnswersToDb(String[] answers, String question, String[] checkbox_option) {
        String SQL_FIND_QUESTION_ID = "select questionId from question where question.description='"+question+"'";

            int questionId = jdbcTemplate.queryForObject(SQL_FIND_QUESTION_ID, Integer.class);
            List<Integer> correctList = new ArrayList<>();
            int corr;
            for(String correct : checkbox_option){
                if (correct.equals("true")){
                    corr = 1;
                }else corr = 0;
                correctList.add(corr);
            }
            for(String answer: answers){
                String SQL_CHECK_IF_ANSWER_EXIST = "SELECT count(answerId) FROM answer WHERE description='"+answer+"'";
                int exist = jdbcTemplate.queryForObject(SQL_CHECK_IF_ANSWER_EXIST, Integer.class);
                if (exist == 0){
                    String SQL_ADD_ANSWER_TO_DB = "insert into answer (description,questionId,correct) values(?,?,?)";
                    jdbcTemplate.update(SQL_ADD_ANSWER_TO_DB, answer, questionId, correctList.get(Arrays.asList(answers).indexOf(answer)));
                }
            }






    }
}
