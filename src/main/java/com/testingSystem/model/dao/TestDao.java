package com.testingSystem.model.dao;

import com.testingSystem.model.entity.Test;

import java.util.List;

public interface TestDao {
    List<Test> getAllTests();
    List<Test> getAllTestsByTopicId(int topicId);
    Test getTestByDescription(String test);
    void addTestToDb(String topic, String test, String[] questions, String testId);
    //void addTest(Test test);
    //Test getTestById(Integer id);
}
