package com.testingSystem.model.entity;

public class User {
    private String sureName;
    private String name;
    private String patronymic;
    private String password;
    private String login;
    private String email;

    public User() {
    }

    public User(String sureName, String name, String patronymic, String password, String login, String email) {
        this.sureName = sureName;
        this.name = name;
        this.patronymic = patronymic;
        this.password = password;
        this.login = login;
        this.email = email;
    }

    public String getSureName() {
        return sureName;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public void setSureName(String sureName) {
        this.sureName = sureName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "sureName='" + sureName + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", password='" + password + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
