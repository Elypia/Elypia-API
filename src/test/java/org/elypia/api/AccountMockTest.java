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
