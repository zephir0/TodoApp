package com.todoapp.account;

import com.todoapp.exceptions.WrongOldPasswordException;
import com.todoapp.todo.TodoRepository;
import com.todoapp.user.User;
import com.todoapp.user.UserRepository;
import com.todoapp.user.UserService;
import com.todoapp.user.dto.UserCredentialsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AccountService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final TodoRepository todoRepository;

    public AccountService(UserService userService,
                          UserRepository userRepository,
                          TodoRepository todoRepository,
                          PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;


    public String getActiveUserLogin() {
        return userService.getLoggedUserName();
    }

    public String getActiveUserPasswordHash() {
        User loggedUser = userService.getLoggedUser();
        return loggedUser.getPassword();
    }

    public void changePassword(UserCredentialsDto userCredentialsDto) {
        String activeUserPassword = userService.getLoggedUser().getPassword();
        if (passwordEncoder.matches(userCredentialsDto.getPassword(), activeUserPassword)
                && userCredentialsDto.getNewPassword().equals(userCredentialsDto.getConfirmPassword())) {

            String newPassword = userCredentialsDto.getNewPassword();
            User user = userService.getLoggedUser();
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        } else throw new WrongOldPasswordException("Wrong old password, or passwords doesnt match");
    }

    @Transactional
    public void deleteAccount() {
        User user = userService.getLoggedUser();
        todoRepository.deleteTodosByUserId(user.getId());
        userRepository.delete(user);
    }
}
