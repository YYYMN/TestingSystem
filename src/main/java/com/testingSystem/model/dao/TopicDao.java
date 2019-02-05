package com.testingSystem.model.dao;

import com.testingSystem.model.entity.Topic;

import java.util.List;

public interface TopicDao {
    List<Topic> getAllTopics();
    Topic getTopicById(Integer topicId);
    void addTopic(Topic topic);
}
