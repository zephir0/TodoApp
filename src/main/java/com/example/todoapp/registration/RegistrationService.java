package com.example.todoapp.registration;

import com.example.todoapp.exceptions.RegistrationPasswordConfirmationException;
import com.example.todoapp.exceptions.UserAlreadyExistException;
import com.example.todoapp.user.User;
import com.example.todoapp.user.UserRepository;
import com.example.todoapp.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    final private UserService userService;
    final private UserRepository userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public RegistrationService(UserService userService,
                               UserRepository userRepo) {
        this.userService = userService;
        this.userRepo = userRepo;
    }

    public void register(RegistrationData registrationData) {
        if (checkIfUserExist(registrationData.getLogin())) {
            throw new UserAlreadyExistException("User already exists for this email");
        }
        if (checkIfPasswordsAreTheSame(registrationData)) {
            User user = new User();
            user.setLogin(registrationData.getLogin());
            String passwordHash = (passwordEncoder.encode(registrationData.getPassword()));
            user.setPassword(passwordHash);
            userRepo.save(user);
        } else
            throw new RegistrationPasswordConfirmationException("Passwords are not the same");

    }

    public boolean checkIfUserExist(String login) {
        return userService.findCredentialsByLogin(login).isPresent();
    }

    public boolean checkIfPasswordsAreTheSame(RegistrationData registrationData) {
        return registrationData.getPassword().equals(registrationData.getPasswordConfirm());
    }
}
