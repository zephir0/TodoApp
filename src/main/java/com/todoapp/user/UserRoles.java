package com.todoapp.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "user_roles")
public class UserRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @OneToMany(mappedBy = "userRolesId")
    @JsonManagedReference
    private Collection<User> users;


    @OneToMany(mappedBy = "userRoles")
    @JsonManagedReference
    private Set<UserRole> userRolesSet;


    public UserRoles() {

    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public Set<UserRole> getUserRolesSet() {
        return userRolesSet;
    }

    public void setUserRolesSet(Set<UserRole> userRolesSet) {
        this.userRolesSet = userRolesSet;
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

}
