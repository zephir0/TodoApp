package com.todoapp.user;

import com.todoapp.todo.TodoRepository;
import com.todoapp.user.dto.UserCredentialsDto;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final TodoRepository todoRepository;

    public UserService(UserRepository userRepository,
                       TodoRepository todoRepository) {
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
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

    public boolean isLoggedUserAdmin() {
        return getLoggedUser().getUserRolesId().getId() == 1;

    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        userRepository.findAll().forEach(usersList::add);
        return usersList;
    }

    public void deleteUserById(Long id) {
        User user = userRepository.findById(id).get();
        todoRepository.deleteTodosByUserId(id);
        userRepository.delete(user);
    }
}
