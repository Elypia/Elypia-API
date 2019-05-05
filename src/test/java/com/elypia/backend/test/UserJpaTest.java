package com.elypia.backend.test;

import com.elypia.backend.database.entities.User;
import com.elypia.backend.database.repositories.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is only testing the code in relation to the database
 * and the entities coming in and out of it though an
 * in-memory (embedded) database.
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserJpaTest {

    private static BCryptPasswordEncoder encoder;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepo;

    @BeforeAll
    public static void beforeAll() {
        encoder = new BCryptPasswordEncoder();
    }

    @BeforeEach
    public void beforeEach() {
        entityManager.clear();
        User user = new User("seth@elypia.com", encoder.encode("AaAa123@@"));
        entityManager.persist(user);
        entityManager.flush();
    }

    @Test
    public void getUser() {
        User user = userRepo.findByEmail("seth@elypia.com");

        assertAll("Match user correctly",
            () -> assertEquals("seth@elypia.com", user.getEmail()),
            () -> assertNull(user.getPhoneNumber()),
            () -> assertTrue(encoder.matches("AaAa123@@", user.getPassword())),
            () -> assertFalse(user.isVerified()),
            () -> assertFalse(user.isAdmin())
        );
    }
}
