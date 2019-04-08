package com.testingSystem.model.daoimpl;

import java.util.List;

import com.testingSystem.model.dao.TopicDao;
import com.testingSystem.model.entity.Topic;
import com.testingSystem.model.mapper.TopicMapper;
import com.testingSystem.spring.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class TopicImpl implements TopicDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TopicImpl(AppConfig config) {
        jdbcTemplate = new JdbcTemplate(config.dataSource());
    }

    public List<Topic> getAllTopics() {
       String SQL_GET_ALL_TOPICS = "select * from testingsystem.topic";
       return jdbcTemplate.query(SQL_GET_ALL_TOPICS, new TopicMapper());
    }


    public void addTopic(Topic topic) {
        String SQL_ADD_TOPIC = "insert into testingsystem.topic (description, name)" +
                "values (?,?)";
        jdbcTemplate.update(SQL_ADD_TOPIC, topic.getDescription(), topic.getTopicName());
    }

    public Topic getTopicById(Integer topicId) {
        String SQL_GET_TOPIC = "select * from testingsystem.topic where topicId = ?";
        return jdbcTemplate.queryForObject(SQL_GET_TOPIC,new TopicMapper(),topicId);
    }


    @Override
    public void updateTopic(Topic topic) {
        String SQL_UPDATE_TOPIC = "UPDATE testingsystem.topic set topic.description = ?, topic.name = ?" +
                " WHERE topic.topicId = ?";

        jdbcTemplate.update(SQL_UPDATE_TOPIC,
                topic.getDescription(), topic.getTopicName(), topic.getTopicId());
    }

    @Override
    public void deleteTopicById(Integer topicId) {
        String SQL_DELETE_TOPIC = "delete from testingsystem.topic where topicId = ?";
        jdbcTemplate.update(SQL_DELETE_TOPIC,topicId);
    }

    public Topic getTopicByDescription(String topic) {
        String SQL_GET_TOPIC_BY_DESCRIPTION = "select * from testingsystem.topic where description ='"+topic+"'";
        return jdbcTemplate.queryForObject(SQL_GET_TOPIC_BY_DESCRIPTION, new TopicMapper());
    }

}
