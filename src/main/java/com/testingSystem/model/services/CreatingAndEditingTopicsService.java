package com.testingSystem.model.services;

import com.testingSystem.model.dao.TopicDao;
import com.testingSystem.model.entity.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreatingAndEditingTopicsService {

    private TopicDao topicDao;

    @Autowired
    public CreatingAndEditingTopicsService(TopicDao topicDao) {
        this.topicDao = topicDao;
    }

    public void CreatingTopic(Topic topic){
        topicDao.addTopic(topic);
    }

    public List<Topic> getAllTopics(){
        return topicDao.getAllTopics();
    }

    public Topic getTopicById(Integer topicId){
        return topicDao.getTopicById(topicId);
    }

    public void updateTopic(Topic topic){
        topicDao.updateTopic(topic);
    }

    public void deleteTopicById(Integer topicId) { topicDao.deleteTopicById(topicId);}

}
