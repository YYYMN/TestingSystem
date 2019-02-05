package com.testingSystem.model.entity;

public class Test {
    private String testName;
    private String description;
    private Integer testId;
    private Integer topicId;

    public Test() {
    }

    public Test(String testName, String description) {
        this.testName = testName;
        this.description = description;
    }

    public String getTestName() {
        return testName;
    }

    public String getDescription() {
        return description;
    }

    public Integer getTestId() {
        return testId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
