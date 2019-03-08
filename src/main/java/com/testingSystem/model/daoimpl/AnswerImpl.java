package com.testingSystem.model.daoimpl;

import com.testingSystem.model.dao.AnswerDao;
import com.testingSystem.model.entity.Answer;
import com.testingSystem.model.entity.Question;
import com.testingSystem.model.mapper.AnswerMapper;
import com.testingSystem.model.mapper.QuestionMapper;
import com.testingSystem.spring.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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

        String SQL_ADD_ANSWER_TO_DB = "insert into answer (description,questionId,correct) values(?,?,?)";
        for (int i = 0; i < answers.length; i++){
            jdbcTemplate.update(SQL_ADD_ANSWER_TO_DB, answers[i], questionId, correctList.get(i));
        }

    }
}
