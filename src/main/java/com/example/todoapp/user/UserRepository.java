package com.example.todoapp.user;

import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByLogin(String login);
}


