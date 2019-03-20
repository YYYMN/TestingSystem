package com.testingSystem.model.mapper;

import com.testingSystem.model.entity.QTConn;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QTConnMapper implements RowMapper<QTConn> {

    @Override
    public QTConn mapRow(ResultSet resultSet, int i) throws SQLException {
        QTConn qtConn = new QTConn();
        qtConn.setQuestionId(resultSet.getInt("questionId"));
        qtConn.setTestId(resultSet.getInt("testId"));
        return qtConn;
    }
}
