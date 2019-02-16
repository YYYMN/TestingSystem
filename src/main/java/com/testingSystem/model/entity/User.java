package com.testingSystem.model.entity;

public class User {
    private Integer userId;
    private String sureName;
    private String name;
    private String password;
    private String login;
    private String role;


    public User() {
    }

    public User(String sureName, String name, String password, String login, String role) {
        this.sureName = sureName;
        this.name = name;
        this.password = password;
        this.login = login;
        this.role = role;
    }

    public String getSureName() {
        return sureName;
    }

    public String getName() {
        return name;
    }


    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public String getRole() {
        return role;
    }

    public void setSureName(String sureName) {
        this.sureName = sureName;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", sureName='" + sureName + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", login='" + login + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
