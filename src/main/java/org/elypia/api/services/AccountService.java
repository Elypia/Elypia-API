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

package org.elypia.api.services;

import org.elypia.api.entities.*;
import org.elypia.api.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author seth@elypia.org (Syed Shah)
 */
@Service
public class AccountService {

    /** Access users via the database. */
    private final AccountRepository accountRepo;

    private final VerificationRepository verifyRepo;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AccountService(AccountRepository accountRepo, VerificationRepository verifyRepo, BCryptPasswordEncoder passwordEncoder) {
        this.accountRepo = accountRepo;
        this.verifyRepo = verifyRepo;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Check if the specified email address is already associated with an account.
     *
     * @param email The email address to check for.
     * @return If there is an account registered with this email address.
     */
    public boolean emailExists(String email) {
        return accountRepo.existsAccountByEmail(email);
    }

    public Account registerNewAccount(String email, String password) {
        Objects.requireNonNull(email);
        Objects.requireNonNull(password);

        if (emailExists(email))
            throw new RuntimeException("This email address is already in use.");

        String hash = passwordEncoder.encode(password);

        Account account = new Account(email, hash);
        accountRepo.save(account);
        return account;
    }

    /**
     * Reset a users password, this could be because the password was forgetten, or
     * because of a situation that calls for all accounts to require a password change.
     *
     * @param token The token of this reset request.
     * @param password The new desired password.
     * @return The account that was affected by this change.
     */
    public Account resetPassword(String token, String password) {
        Objects.requireNonNull(token);
        Objects.requireNonNull(password);

        String hash = passwordEncoder.encode(password);

        VerificationToken veritifcationToken = verifyRepo.findByToken(token);
        Account account = veritifcationToken.getAccount();

        account.setPassword(hash);
        return accountRepo.save(account);
    }
}
