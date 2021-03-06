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

package org.elypia.api.controllers;

import org.elypia.api.persistence.entities.Account;
import org.elypia.api.persistence.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author seth@elypia.org (Syed Shah)
 */
@RequestMapping("/api/accounts")
@RestController
public class UserController {

    private AccountRepository accountRepo;

    @Autowired
    public UserController(AccountRepository accountRepo) {
        this.accountRepo = accountRepo;
    }

    @GetMapping
    public Account getAccount(@RequestParam int userId) {
        return accountRepo.findById(userId).get();
    }
}
