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

package org.elypia.api.persistence.entities;

import javax.persistence.*;
import java.util.List;

/**
 * @author seth@elypia.org (Syed Shah)
 */
@Entity
@Table(name = "accounts")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(name = "account_id")
    private int accountId;

    @OneToMany
    @Column(name = "username")
    private List<Username> usernames;

    public User() {
        // Do nothing
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public List<Username> getUsernames() {
        return usernames;
    }

    public void setUsername(List<Username> usernames) {
        this.usernames = usernames;
    }
}
