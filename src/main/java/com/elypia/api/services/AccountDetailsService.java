/*
 * Copyright (C) 2019-2019  Elypia
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

package com.elypia.api.services;

import com.elypia.api.entities.Account;
import com.elypia.api.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Our implementation of {@link UserDetailsService}.
 * Despite the name, this is for authenticating to an Elypia {@link Account},
 * not an Elypia user.
 */
@Service
public class AccountDetailsService implements UserDetailsService {

    private final AccountRepository accountRepo;

    @Autowired
    public AccountDetailsService(final AccountRepository accountRepo) {
        this.accountRepo = accountRepo;
    }

    /**
     * Accounts do not have a username, but rather an email address.
     *
     * @param email The email address of the account to authenticate.
     * @return The account details.
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {
        Account account = accountRepo.findByEmail(email);

        if (account == null)
            throw new UsernameNotFoundException("Account with that email not found.");

        return new User(account.getEmail(), account.getPassword(), List.of());
    }
}
