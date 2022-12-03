package com.todoapp.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRolesRepository extends CrudRepository<UserRoles, Long> {
    Optional<UserRoles> findByDescription(String description);
}
