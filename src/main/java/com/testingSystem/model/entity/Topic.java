package com.testingSystem.model.entity;

public class Topic {
    private String topicName;
    private String description;
    private Integer topicId;

    public Topic() {
    }

    public Topic(String topicName, String description) {
        this.topicName = topicName;
        this.description = description;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }
}
