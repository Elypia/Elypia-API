/*
 * Copyright (C) 2019  Elypia CIC
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

package org.elypia.api;

import org.elypia.api.controllers.UserController;
import org.elypia.api.entities.Account;
import org.elypia.api.repositories.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This this is mocking the database and simulating
 * HTTP requests in order to ensure we're getting the response statuses
 * and body we're expecting with the given scenarios as well as aid
 * in documentation generated.
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class AccountMvcTest {

    private static BCryptPasswordEncoder encoder;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AccountRepository userRepo;

    @MockBean
    private VerificationRepository verifyRepo;

    @BeforeAll
    public static void beforeAll() {
        encoder = new BCryptPasswordEncoder();
    }

    @Test
    public void emailNotExist() throws Exception {
        mvc.perform(get("/api/users/exists").param("email", "nou@nou.nou"))
            .andExpect(status().isNotFound());
    }

    @Test
    public void emailDoesExist() throws Exception {
        Account account = new Account(1, "seth@elypia.org", encoder.encode("@123pass@"), true, true);
        Mockito.when(userRepo.findByEmail("seth@elypia.org")).thenReturn(account);

        mvc.perform(get("/api/users/exists").param("email", "seth@elypia.org"))
            .andExpect(status().isNoContent());
    }
}
