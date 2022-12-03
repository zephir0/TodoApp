package com.todoapp.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.todoapp.todo.Todo;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "application_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String login;
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @JsonBackReference
    private UserRoles userRolesId;


    //IGNORES THIS PROPERTY IN JSON FILE DUE TO INFINITE LOOP

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    @JsonIgnoreProperties("users")
    private Set<UserRoles> userAllRoles = new HashSet<>();


    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private Collection<Todo> todoCollections;

    public User() {
    }

    public UserRoles getUserRolesId() {
        return userRolesId;
    }

    public void setUserRolesId(UserRoles userRolesId) {
        this.userRolesId = userRolesId;
    }

    public Set<UserRoles> getUserAllRoles() {
        return userAllRoles;
    }

    public void setUserAllRoles(Set<UserRoles> userAllRoles) {
        this.userAllRoles = userAllRoles;
    }

    public void setTodoCollections(Collection<Todo> todoCollections) {
        this.todoCollections = todoCollections;
    }

    public Collection<Todo> getTodoCollections() {
        return todoCollections;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String userRole() {
        String[] strings = userAllRoles.stream().map(UserRoles::getDescription).toArray(String[]::new);
        return Arrays.toString(strings);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", userRolesId=" + userRolesId +
                ", userAllRoles=" + userAllRoles +
                ", todoCollections=" + todoCollections +
                '}';
    }
}
