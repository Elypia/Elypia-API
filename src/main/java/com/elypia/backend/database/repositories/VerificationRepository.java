package com.elypia.backend.database.repositories;

import com.elypia.backend.database.entities.VerificationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationRepository extends CrudRepository<VerificationToken, Integer> {

    VerificationToken findByToken(String token);
}
