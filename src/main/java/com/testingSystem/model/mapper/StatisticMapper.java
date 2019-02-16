package com.testingSystem.model.mapper;

import com.testingSystem.model.entity.Statistic;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StatisticMapper implements RowMapper<Statistic> {

    @Override
    public Statistic mapRow(ResultSet resultSet, int i) throws SQLException {
        Statistic statistic = new Statistic();
        statistic.setStatisticId(resultSet.getInt("statisticId"));
        statistic.setQuestionId(resultSet.getInt("questionId"));
        statistic.setUserId(resultSet.getInt("userId"));
        statistic.setCorrect(resultSet.getBoolean("correct"));
        statistic.setDate(resultSet.getDate("data"));
        return statistic;
    }
}
