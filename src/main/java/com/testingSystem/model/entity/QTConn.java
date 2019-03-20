package com.testingSystem.model.entity;

public class QTConn {
    private int qtconn_id;
    private int testId;
    private int questionId;

    public QTConn() {
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
}
