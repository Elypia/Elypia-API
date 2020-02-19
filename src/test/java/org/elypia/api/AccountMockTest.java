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
import org.elypia.api.entities.Account;
import org.elypia.api.repositories.AccountRepository;
import org.junit.jupiter.api.Test;
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
 *
 * @author seth@elypia.org (Syed Shah)
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AccountMockTest {

    @MockBean
    private AccountRepository userRepo;

    @Autowired
    private UserController controller;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Test
    public void getUserTest() {
        Account account = new Account(1, "seth@elypia.org", encoder.encode("AaAa123@@"), true, true);
        Mockito.when(userRepo.findById(1)).thenReturn(account);
        Account resp = controller.getAccount(1);

        assertAll("Match user correctly",
            () -> assertEquals(1, resp.getId()),
            () -> assertEquals("seth@elypia.org", resp.getEmail()),
            () -> assertNull(resp.getPhoneNumber()),
            () -> assertTrue(encoder.matches("AaAa123@@", resp.getPassword())),
            () -> assertTrue(resp.isVerified()),
            () -> assertTrue(resp.isAdmin())
        );
    }
}
