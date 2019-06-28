package com.testingSystem.model.daoimpl;

import com.testingSystem.model.dao.LiteratureDao;
import com.testingSystem.model.entity.Literature;
import com.testingSystem.model.mapper.LiteratureMapper;
import com.testingSystem.spring.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class LiteratureImpl implements LiteratureDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public LiteratureImpl(AppConfig config) {
        jdbcTemplate = new JdbcTemplate(config.dataSource());
    }


    @Override
    public Literature getLiteratureByQuestionId(int questionId) {

        String SQL_GET_Literature = "select * from testingsystem.literature where questionId = ?";
        return jdbcTemplate.queryForObject(SQL_GET_Literature, new LiteratureMapper(), questionId);

    }
}
