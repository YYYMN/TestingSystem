package com.testingSystem.model.entity;

public class Question {
    private Integer questionId;
    private String description;
    private Integer testId;

    public Question() {
    }

    public Question(String description, Integer testId) {
        this.description = description;
        this.testId = testId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public String getDescription() {
        return description;
    }

    public Integer getTestId() {
        return testId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }
}
