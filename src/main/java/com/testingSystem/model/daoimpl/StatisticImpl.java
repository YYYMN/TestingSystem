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
        String SQL_GET_ALL_STATISTICS_BY_QUESTINID = "select * from testingsystem.statistic where questionId = ?";
        return jdbcTemplate.query(SQL_GET_ALL_STATISTICS_BY_QUESTINID,new Object[]{questionId}, new StatisticMapper());
    }
}
