package com.testingSystem.model.daoimpl;

import com.testingSystem.model.dao.QuestionDao;
import com.testingSystem.model.entity.QTConn;
import com.testingSystem.model.entity.Question;
import com.testingSystem.model.mapper.QTConnMapper;
import com.testingSystem.model.mapper.QuestionMapper;
import com.testingSystem.spring.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionImpl implements QuestionDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public QuestionImpl(AppConfig config) {
        jdbcTemplate = new JdbcTemplate(config.dataSource());
    }

    public List<Question> getAllQuestionsByTestId(Integer testId) {
        List<Question> questions = new ArrayList<>();
        List<Integer> questionsId = new ArrayList<>();
        String SQL_GET_ALL_QUESTIONSID_BY_TESTID = "select * from testingsystem.qt_conn where testId = ?";
        List<QTConn> qtConnList = jdbcTemplate.query(SQL_GET_ALL_QUESTIONSID_BY_TESTID, new Integer[]{testId}, new QTConnMapper());
        for(QTConn qtConn : qtConnList){
            questionsId.add(qtConn.getQuestionId());
        }
        for(Integer questionId : questionsId){
            String SQL_GET_ALL_QUESTIONS_BY_QUESTIONSID = "select * from testingsystem.question where questionId ="+questionId+"";
            questions.add(jdbcTemplate.queryForObject(SQL_GET_ALL_QUESTIONS_BY_QUESTIONSID, new QuestionMapper()));
        }
       return questions;
    }

    public Question getQuestionByTestIdAndQuestionId(int testId, int questionId) {
            String SQL_GET_QUESTION_BY_TESTID_AND_QUESTIONID = "SELECT * FROM question INNER JOIN qt_conn on question.questionId = qt_conn.questionId\n" +
                    "WHERE testId = " + testId + " AND qt_conn.questionId = " + questionId;

        return jdbcTemplate.queryForObject(SQL_GET_QUESTION_BY_TESTID_AND_QUESTIONID, new QuestionMapper());
    }

    @Override
    public List<Integer> getAllQuestionsIdByTestId(int testId) {
        String SQL_GET_ALL_QUESTIONID_BY_TESTID = "SELECT questionId FROM qt_conn WHERE testId = " + testId;

        List<Integer> integers = jdbcTemplate.queryForList(SQL_GET_ALL_QUESTIONID_BY_TESTID, Integer.TYPE);
        return integers;
    }

    @Override
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

    @Override
    public List<Integer> getQuestionsIdByDescriptions(String[] questions) {

        List<Integer> questionsId = new ArrayList<>();
        for(String question : questions){
            String SQL_GET_QUESTIONID_BY_DESCRIPTION = "select questionId from question where description='"+question+"'";
            questionsId.add(jdbcTemplate.queryForObject(SQL_GET_QUESTIONID_BY_DESCRIPTION, Integer.class));
        }
        return questionsId;
    }

    @Override
    public void addQuestionsAndTestIdToQTConnection(List<Integer> questionsId, String test) {
        for(int questionId : questionsId){
            String SQL_GET_TESTID_BY_DESCRIPTION = "select max(testId) from testingsystem.test where description ='"+test+"'";
            String SQL_ADD_TEST_AND_QUESTION_ID_TO_QTCONN = "insert into qt_conn (testId,questionId) values(?,?)";
            int testId = jdbcTemplate.queryForObject(SQL_GET_TESTID_BY_DESCRIPTION, Integer.class);
            jdbcTemplate.update(SQL_ADD_TEST_AND_QUESTION_ID_TO_QTCONN, testId, questionId);

        }

    }

    @Override
    public List<Question> getAllQuestions() {
        String SQL_GET_ALL_QUESTIONS = "select * from testingsystem.question";
        return jdbcTemplate.query(SQL_GET_ALL_QUESTIONS, new QuestionMapper());
    }
}
