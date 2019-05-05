package com.elypia.backend.database.repositories;

import com.elypia.backend.database.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findById(int id);
    User findByEmail(String email);
}
