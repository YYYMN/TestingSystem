package com.testingSystem.model.entity;

public class Literature {
    private int literatureId;
    private String description;
    private int questionId;

    public Literature() {
    }

    public Literature(String description, int questionId) {
        this.description = description;
        this.questionId = questionId;
    }

    public int getLiteratureId() {
        return literatureId;
    }

    public void setLiteratureId(int literatureId) {
        this.literatureId = literatureId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Override
    public String toString() {
        return "Literature{" + literatureId +
                ", description='" + description + '\'' +
                ", questionId=" + questionId +
                '}';
    }
}
