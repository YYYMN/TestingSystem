package com.testingSystem.model.mapper;
import com.testingSystem.model.entity.Link;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LinkMapper implements RowMapper<Link>{
    @Override
    public Link mapRow(ResultSet resultSet, int i) throws SQLException {
        Link link = new Link();
        link.setLinkId(resultSet.getInt("linkId"));
        link.setLink(resultSet.getString("link"));
        link.setLiteratureId(resultSet.getInt("literatureId"));
        return link;
    }
}
