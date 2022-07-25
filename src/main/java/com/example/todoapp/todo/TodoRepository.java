package com.example.todoapp.todo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {
    void deleteTodosByUserId(Long id);
}
