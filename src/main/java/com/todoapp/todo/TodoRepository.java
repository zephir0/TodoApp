package com.todoapp.todo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {
    void deleteTodosByUserId(Long id);
    void deleteTodosByUserLogin(String login);
}
