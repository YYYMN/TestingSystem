package com.testingSystem.model.dao;

import com.testingSystem.model.entity.Test;

import java.util.List;

public interface TestDao {
    List<Test> getAllTests();

    List<Test> getAllTestsByTopicId(int topicId);

    Test getTestByDescription(String test);

    int getCountOfQuestionsInTest(String testId);

    int getCountOfQuestionsInCorrectTest(String test);

    void addNewTestToDB(String topic, String test, String[] questions);

    void addNewCorrectTestToDB(String test, String topic);

    void updateTest(String test, int testId);
    //void addTest(Test test);
    //Test getTestById(Integer id);

}
