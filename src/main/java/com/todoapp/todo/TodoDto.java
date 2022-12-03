package com.todoapp.todo;


import javax.validation.constraints.NotEmpty;
import java.time.LocalTime;

public class TodoDto {
    @NotEmpty
    private Long id;
    @NotEmpty
    private String description;
    private LocalTime localTime;



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
}
