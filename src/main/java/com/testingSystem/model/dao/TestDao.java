package com.testingSystem.model.dao;

import com.sun.tools.javac.util.List;
import com.testingSystem.model.entity.Test;

public interface TestDao {
    List<Test> getAllTests();
    void addTest(Test test);
    Test getTestById(Integer id);
}
