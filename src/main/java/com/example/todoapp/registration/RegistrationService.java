package com.example.todoapp.registration;

import com.example.todoapp.user.User;
import com.example.todoapp.user.UserRepository;
import com.example.todoapp.user.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    final private UserService userService;
    final private UserRepository userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public RegistrationService(UserService userService, UserRepository userRepo) {
        this.userService = userService;
        this.userRepo = userRepo;
    }

    public void register(RegistrationData registrationData) {
        if (checkIfUserExist(registrationData.getLogin())) {
            throw new RuntimeException("User already exists for this email");
        }
        User user = new User();
        user.setLogin(registrationData.getLogin());
        String passwordHash = (passwordEncoder.encode(registrationData.getPassword()));
        user.setPassword(passwordHash);
        userRepo.save(user);
    }

    public boolean checkIfUserExist(String login) {
        return userService.findCredentialsByLogin(login).isPresent();
    }
}
