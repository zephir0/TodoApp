package com.example.todoapp.user.dto;

import java.util.Set;

public class UserCredentialsDto {
    private final String login;
    private final String password;
    private final String newPassword;
    private final String confirmPassword;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserCredentialsDto(String login,
                              String password,
                              String newPassword,
                              String confirmPassword,
                              Set<String> roles) {
        this.login = login;
        this.password = password;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
        this.roles = roles;
    }

    private final Set<String> roles;

    public String getNewPassword() {
        return newPassword;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Set<String> getRoles() {
        return roles;
    }
}
