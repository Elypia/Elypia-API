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

import org.elypia.api.persistence.entities.Account;
import org.elypia.api.persistence.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Our implementation of {@link UserDetailsService}.
 * Despite the name, this is for authenticating to an Elypia {@link Account},
 * not an Elypia user.
 *
 * @author seth@elypia.org (Syed Shah)
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
