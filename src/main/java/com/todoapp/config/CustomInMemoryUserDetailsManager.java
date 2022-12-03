package com.todoapp.config;

import com.todoapp.user.UserRoles;
import com.todoapp.user.dto.UserCredentialsDto;
import com.todoapp.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomInMemoryUserDetailsManager implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public CustomInMemoryUserDetailsManager(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userService.
                findCredentialsByLogin(login)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with login %s not found", login)));
    }


    private UserDetails createUserDetails(UserCredentialsDto credentialsDto) {
        return User.builder()
                .username(credentialsDto.getLogin())
                .password(credentialsDto.getPassword())
                .roles(credentialsDto.getRoles())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
