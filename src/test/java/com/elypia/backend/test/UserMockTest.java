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

import com.elypia.backend.controllers.UserController;
import com.elypia.backend.entities.User;
import com.elypia.backend.repositories.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Instead of using an in-memory database we just fake database
 * responses and use the fake responses to test our controller methods.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserMockTest {

    private static BCryptPasswordEncoder encoder;

    @Autowired
    private UserController controller;

    @MockBean
    private UserRepository userRepo;

    @BeforeAll
    public static void beforeAll() {
        encoder = new BCryptPasswordEncoder();
    }

    @Test
    public void getUserTest() {
        User user = new User(1, "seth@elypia.com", encoder.encode("@123pass@"), true, true);
        Mockito.when(userRepo.findById(1)).thenReturn(user);
        User resp = controller.getUser(1);

        assertAll("Match user correctly",
            () -> assertEquals(1, resp.getId()),
            () -> assertEquals("seth@elypia.com", resp.getEmail()),
            () -> assertNull(resp.getPhoneNumber()),
            () -> assertFalse(encoder.matches("AaAa123@@", resp.getPassword())),
            () -> assertTrue(resp.isVerified()),
            () -> assertTrue(resp.isAdmin())
        );
    }
}
