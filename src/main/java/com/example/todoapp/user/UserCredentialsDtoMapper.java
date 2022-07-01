package com.example.todoapp.user;


import java.util.Set;
import java.util.stream.Collectors;

public class UserCredentialsDtoMapper {
    static UserCredentialsDto map(UserEntity user) {
        String login = user.getLogin();
        String password = user.getPassword();
        Set<String> roles = user.getRoles().stream().map(UserRole::getName).collect(Collectors.toSet());
        return new UserCredentialsDto(login, password, roles);
    }
}
