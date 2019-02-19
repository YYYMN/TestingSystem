package com.testingSystem.model.entity;

public class User {
    private Integer userId;
    private String firstName;
    private String lastName;
    private String password;
    private String login;
    private String role;


    public User() {
    }

    public User(String firstName, String lastName, String password, String login, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.login = login;
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getUserId() {
        return userId;
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

    public void setSurName(String firstName) {
        this.firstName = firstName;
    }

    public void setName(String lastName) {
        this.lastName = lastName;
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
                ", sureName='" + firstName + '\'' +
                ", name='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", login='" + login + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
