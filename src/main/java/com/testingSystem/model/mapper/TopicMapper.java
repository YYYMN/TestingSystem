package com.testingSystem.model.mapper;

import com.testingSystem.model.entity.Topic;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TopicMapper implements RowMapper<Topic> {

    @Override
    public Topic mapRow(ResultSet resultSet, int i) throws SQLException {
        Topic topic = new Topic();
        topic.setTopicName(resultSet.getString("name"));
        topic.setDescription(resultSet.getString("description"));
        topic.setTopicId(resultSet.getInt("topicId"));
        return topic;
    }
}
