package com.example.todoapp.user;

import com.example.todoapp.user.dto.UserCredentialsDto;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }


    public Optional<UserCredentialsDto> findCredentialsByLogin(String login) {
        return userRepository
                .findByLogin(login)
                .map(UserCredentialsDtoMapper::map);
    }

    public String getLoggedUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


    public User getLoggedUser() {
        Optional<User> byLogin = userRepository.findByLogin(getLoggedUserName());
        return byLogin.get();
    }
}
