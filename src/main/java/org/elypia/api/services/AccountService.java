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
