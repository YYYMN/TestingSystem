package com.testingSystem.model.entity;

public class Question {
    private Integer questionId;
    private String description;


    public Question() {
    }

    public Question(String description) {
        this.description = description;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public String getDescription() {
        return description;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
