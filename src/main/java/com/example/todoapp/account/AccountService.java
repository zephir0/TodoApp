package com.example.todoapp.account;

import com.example.todoapp.exceptions.WrongOldPasswordException;
import com.example.todoapp.user.User;
import com.example.todoapp.user.UserRepository;
import com.example.todoapp.user.UserService;
import com.example.todoapp.user.dto.UserCredentialsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final UserService userService;
    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AccountService(UserService userService,
                          UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }


    public String getLoginName() {
        User loggedUser = userService.findUser();
        return loggedUser.getLogin();
    }

    public String getPasswordHash() {
        User loggedUser = userService.findUser();
        return loggedUser.getPassword();
    }

    public void changePassword(UserCredentialsDto userCredentialsDto) {
        String activeUserPassword = userService.findUser().getPassword();
        if (passwordEncoder.matches(userCredentialsDto.getPassword(), activeUserPassword)
                && userCredentialsDto.getNewPassword().equals(userCredentialsDto.getConfirmPassword())) {

            String newPassword = userCredentialsDto.getNewPassword();
            User user = userService.findUser();
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        } else throw new WrongOldPasswordException("Wrong old password, or passwords doesnt match");
    }

    public void accountDelete() {
        User user = userService.findUser();
        userRepository.delete(user);
    }
}
