package com.testingSystem.model.entity;

public class Topic {
    private String topicName;
    private String desription;
    private Integer topicId;

    public Topic() {
    }

    public Topic(String topicName, String desription) {
        this.topicName = topicName;
        this.desription = desription;
    }

    public String getTopicName() {
        return topicName;
    }

    public String getDesription() {
        return desription;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public void setDesription(String desription) {
        this.desription = desription;
    }

}
