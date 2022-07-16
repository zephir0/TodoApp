package com.example.todoapp.account;

import com.example.todoapp.user.User;
import com.example.todoapp.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final UserService userService;

    public AccountService(UserService userService) {
        this.userService = userService;
    }

    public String accountDetailsMessage() {
        User loggedUser = userService.findUserId();
        String userLogin = loggedUser.getLogin();
        String userPassword = loggedUser.getPassword();
        return "Twoje dane do konta, login: " + userLogin + ", password: " + userPassword;

    }
}
