package com.example.todoapp.todo;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "todo_list")
public class TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private LocalTime localTime;


    public TodoEntity(Long id,
                      String description,
                      LocalTime localTime) {
        this.id = id;
        this.description = description;
        this.localTime = localTime;
    }

    public TodoEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    @Override
    public String toString() {
        return id +
                ", " + description +
                ", " + localTime;
    }
}
