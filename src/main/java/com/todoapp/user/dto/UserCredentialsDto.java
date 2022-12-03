package com.todoapp.user.dto;

import java.util.Set;

public class UserCredentialsDto {
    private final String login;
    private final String password;
    private final String newPassword;
    private final String confirmPassword;
    private final String role;

    public UserCredentialsDto(String login,
                              String password,
                              String newPassword,
                              String confirmPassword,
                              String role) {
        this.login = login;
        this.password = password;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
        this.role = role;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getRoles() {
        return role;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }


}
