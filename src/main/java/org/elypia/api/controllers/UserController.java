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

package org.elypia.api.controllers;

import org.elypia.api.entities.Account;
import org.elypia.api.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return accountRepo.findById(userId);
    }
}
