package com.example.todoapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
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
