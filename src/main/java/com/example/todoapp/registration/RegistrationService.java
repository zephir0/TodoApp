package com.example.todoapp.registration;

import com.example.todoapp.user.User;
import com.example.todoapp.user.UserRepository;
import com.example.todoapp.user.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    final private UserService userService;
    final private UserRepository userRepo;

    final private PasswordEncoder passwordEncoder;

    public RegistrationService(UserService userService, UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegistrationData registrationData) {
        if (checkIfUserExist(registrationData.getLogin())) {
            throw new RuntimeException("User already exists for this email");
        }
        User user = new User();
        user.setLogin(registrationData.getLogin());
        String passwordHash = passwordEncoder.encode(registrationData.getPassword());
        user.setPassword(passwordHash);
        userRepo.save(user);
    }

   /* public void encodePassword(User user, RegistrationData registrationData) {
        user.setPassword(passwordEncoder.encode(registrationData.getPassword()));
    }*/

    public boolean checkIfUserExist(String login) {
        return userService.findCredentialsByLogin(login).isPresent();
    }
}
