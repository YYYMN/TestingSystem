package com.testingSystem.model.daoimpl;

import com.testingSystem.model.dao.QTConnDao;
import com.testingSystem.spring.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class QTConnImpl implements QTConnDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public QTConnImpl(AppConfig config) {
        jdbcTemplate = new JdbcTemplate(config.dataSource());
    }

    @Override
    public void addTestIdAndQuestionIdToCTConn(int testId, int questionId) {
        String SQL_ADD_TEST_ID_AND_QUESTION_ID_TO_CTCONN =
                "INSERT INTO testingsystem.qt_conn (testId, questionId) values (?,?)";
        jdbcTemplate.update(SQL_ADD_TEST_ID_AND_QUESTION_ID_TO_CTCONN, testId, questionId);
//        String SQL_ADD_TEST_AND_QUESTION_ID_TO_QTCONN = "insert into qt_conn (testId,questionId) values(?,?)";
//        jdbcTemplate.update(SQL_ADD_TEST_AND_QUESTION_ID_TO_QTCONN, testId, questionId);

    }
}
