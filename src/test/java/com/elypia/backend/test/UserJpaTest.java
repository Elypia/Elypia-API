/*
 * Copyright (C) 2019  Elypia
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.elypia.backend.test;

import com.elypia.backend.entities.User;
import com.elypia.backend.repositories.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
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
