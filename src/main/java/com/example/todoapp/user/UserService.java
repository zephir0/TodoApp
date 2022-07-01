package com.example.todoapp.user;

import com.example.todoapp.user.dto.UserCredentialsDto;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }


    public Optional<UserCredentialsDto> findCredentialsByLogin(String login) {
        return repository
                .findByLogin(login)
                .map(UserCredentialsDtoMapper::map);
    }
}
