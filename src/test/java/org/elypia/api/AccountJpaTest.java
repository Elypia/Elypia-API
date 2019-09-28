/*
 * Elypia API - Backend for Elypia website and core services.
 * Copyright (C) 2019-2019  Elypia CIC
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

import org.elypia.api.entities.Account;
import org.elypia.api.repositories.AccountRepository;
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
 *
 * @author seth@elypia.org (Syed Shah)
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class AccountJpaTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccountRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @BeforeEach
    public void beforeEach() {
        entityManager.clear();
        Account account = new Account(1, "seth@elypia.org", encoder.encode("AaAa123@@"));
        entityManager.persist(account);
        entityManager.flush();
    }

    @Test
    public void getUser() {
        Account account = userRepo.findByEmail("seth@elypia.org");

        assertAll("Match user correctly",
            () -> assertEquals(1, account.getId()),
            () -> assertEquals("seth@elypia.org", account.getEmail()),
            () -> assertTrue(encoder.matches("AaAa123@@", account.getPassword())),
            () -> assertNull(account.getPhoneNumber()),
            () -> assertFalse(account.isVerified()),
            () -> assertFalse(account.isAdmin())
        );
    }
}
