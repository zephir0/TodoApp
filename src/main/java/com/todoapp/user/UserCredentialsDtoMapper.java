package com.todoapp.user;


import com.todoapp.user.dto.UserCredentialsDto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserCredentialsDtoMapper {
    static UserCredentialsDto map(User user) {
        String login = user.getLogin();
        String password = user.getPassword();
        String description = user.getUserRolesId().getDescription();
        return new UserCredentialsDto(login, password, password, password, description);
    }
}
