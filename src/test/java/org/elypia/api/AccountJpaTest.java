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
