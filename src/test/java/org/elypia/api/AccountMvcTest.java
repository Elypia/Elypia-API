/*
 * Copyright 2019-2020 Elypia CIC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.elypia.api;

import org.elypia.api.controllers.UserController;
import org.elypia.api.persistence.entities.Account;
import org.elypia.api.persistence.repositories.*;
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
 *
 * @author seth@elypia.org (Syed Shah)
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
