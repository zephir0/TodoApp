package com.todoapp.registration;

import com.todoapp.exceptions.RegPassConfirmationException;
import com.todoapp.exceptions.UserAlreadyExistException;
import com.todoapp.exceptions.UserRolesNotFoundException;
import com.todoapp.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationService {
    private static final String USER_ROLE = "USER";
    private static final String ADMIN_AUTHORITY = "ADMIN";
    final private UserService userService;
    final private UserRepository userRepo;
    final private UserRolesRepository userRolesRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public RegistrationService(UserService userService,
                               UserRepository userRepo,
                               UserRolesRepository userRolesRepository,
                               PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRepo = userRepo;
        this.userRolesRepository = userRolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegistrationData registrationData) {

        if (checkIfUserExist(registrationData.getLogin())) {
            throw new UserAlreadyExistException("User already exists with this email");
        }

        if (checkIfPasswordsAreTheSame(registrationData)) {
            User user = new User();
            user.setLogin(registrationData.getLogin());
            String passwordHash = (passwordEncoder
                    .encode(registrationData.getPassword()));
            user.setPassword(passwordHash);

            Optional<UserRoles> userRolesRepositoryByDescription = userRolesRepository
                    .findByDescription(USER_ROLE);
            userRolesRepositoryByDescription.ifPresentOrElse(
                    role -> user
                            .getUserAllRoles()
                            .add(role),
                    () -> {
                        throw new UserRolesNotFoundException("User role not found.");
                    });
            user.setUserRolesId(userRolesRepositoryByDescription.get());
            userRepo.save(user);
        } else
            throw new RegPassConfirmationException("Passwords are not the same");
    }

    public boolean checkIfUserExist(String login) {
        return userService.findCredentialsByLogin(login).isPresent();
    }

    public boolean checkIfPasswordsAreTheSame(RegistrationData registrationData) {
        return registrationData.getPassword().equals(registrationData.getPasswordConfirm());
    }
}
