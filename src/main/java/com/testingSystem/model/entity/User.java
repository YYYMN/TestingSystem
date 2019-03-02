package com.testingSystem.model.entity;

public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private String password;
    private String login;
    private int roleId;

    public User() {
    }

    public User(String firstName, String lastName, String password, String login, int roleId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.login = login;
        this.roleId = roleId;
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

    public int getRoleId() {
        return roleId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setRole(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", sureName='" + firstName + '\'' +
                ", name='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", login='" + login + '\'' +
                ", role='" + roleId + '\'' +
                '}';
    }

}
