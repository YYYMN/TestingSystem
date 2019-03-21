package com.testingSystem.model.daoimpl;

import com.testingSystem.model.dao.StatisticDao;
import com.testingSystem.model.entity.Statistic;
import com.testingSystem.model.mapper.StatisticMapper;
import com.testingSystem.spring.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StatisticImpl implements StatisticDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public StatisticImpl(AppConfig config){ jdbcTemplate = new JdbcTemplate(config.dataSource()); }


    @Override
    public List<Statistic> getAllStatisticByQuestionId(Integer questionId) {
        String SQL_GET_ALL_STATISTICS_BY_QUESTION_ID = "select * from testingsystem.statistic where questionId = ?";
        return jdbcTemplate.query(SQL_GET_ALL_STATISTICS_BY_QUESTION_ID, new StatisticMapper(),questionId);
    }

    @Override
    public List<Statistic> getAllStatisticByUserId(Integer userId) {
        String SQL_GET_ALL_STATISTICS_BY_USER_ID = "select * from testingsystem.statistic where userId = ?";
        return jdbcTemplate.query(SQL_GET_ALL_STATISTICS_BY_USER_ID,new StatisticMapper(),userId);
    }

    @Override
    public List<Statistic> getAllStatistic() {
        String SQL_GET_ALL_STATISTICS = "select * from testingsystem.statistic ";
        return jdbcTemplate.query(SQL_GET_ALL_STATISTICS,new StatisticMapper());
    }
}
