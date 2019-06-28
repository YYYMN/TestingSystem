package com.testingSystem.model.entity;

public class Link {
    private int linkId;
    private String link;
    private int literatureId;

    public Link() {
    }

    public Link(String link, int literatureId) {
        this.link = link;
        this.literatureId = literatureId;
    }

    public int getLinkId() {
        return linkId;
    }

    public void setLinkId(int linkId) {
        this.linkId = linkId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getLiteratureId() {
        return literatureId;
    }

    public void setLiteratureId(int literatureId) {
        this.literatureId = literatureId;
    }

    @Override
    public String toString() {
        return "Link{" +
                "link='" + link + '\'' +
                ", literatureId=" + literatureId +
                '}';
    }
}
