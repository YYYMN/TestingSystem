package com.testingSystem.model.entity;

import java.util.Date;
import java.util.Objects;

public class Statistic {
    private Integer statisticId;
    private Date date;
    private boolean correct;
    private Integer questionId;
    private Integer userId;
    private Integer testId;

    public Statistic(){

    }

    public Statistic(Date date, boolean correct, Integer questionId, Integer userId, Integer testId) {
        this.date = date;
        this.correct = correct;
        this.questionId = questionId;
        this.userId = userId;
        this.testId = testId;
    }

    public Integer getStatisticId() {
        return statisticId;
    }

    public Date getDate() {
        return date;
    }

    public boolean isCorrect() {
        return correct;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getTestId() {
        return testId;
    }

    public void setStatisticId(Integer statisticId) {
        this.statisticId = statisticId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistic statistic = (Statistic) o;
        return questionId.equals(statistic.questionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId);
    }
}
