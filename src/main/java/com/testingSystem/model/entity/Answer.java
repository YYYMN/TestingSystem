package com.testingSystem.model.entity;

public class Answer {
    private int answerId;
    private String description;
    private int correct;
    private int questionId;

    public Answer(){}

    public Answer(String description, int correct, int questionId) {
        this.description = description;
        this.correct = correct;
        this.questionId = questionId;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
}
