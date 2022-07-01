package com.example.todoapp.user;

import java.util.Set;

public class UserCredentialsDto {
    private final String login;
    private final String password;
    private final Set<String> roles;

    public UserCredentialsDto(String login,
                              String password,
                              Set<String> roles) {
        this.login = login;
        this.password = password;
        this.roles = roles;
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
