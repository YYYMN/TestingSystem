package com.testingSystem.model.daoimpl;

import com.testingSystem.model.dao.LinkDao;
import com.testingSystem.model.entity.Link;
import com.testingSystem.model.mapper.LinkMapper;
import com.testingSystem.spring.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class LinkImpl implements LinkDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public LinkImpl(AppConfig config) {
        jdbcTemplate = new JdbcTemplate(config.dataSource());
    }

    @Override
    public Link getLinkByLiteratureId(int literatureId) {
        String SQL_GET_LINK_BY_LITERATURE_ID = "select * from testingsystem.link where literatureId = ?";
        return jdbcTemplate.queryForObject(SQL_GET_LINK_BY_LITERATURE_ID, new LinkMapper(), literatureId);

    }
}
