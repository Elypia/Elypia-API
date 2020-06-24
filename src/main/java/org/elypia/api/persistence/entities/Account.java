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

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * An account represents a single person on the service.
 * An account tends to hold any personal information or the password
 * rather than {@link User users} which only hold Elypia related things.
 *
 * A single {@link Account} can have multiple {@link User users}.
 *
 * @author seth@elypia.org (Syed Shah)
 */
@Entity
@Table(name = "accounts")
public class Account {

    @Min(1)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int id;

    @NotNull
    @Length(min = 5)
    @Email
    @Column(name = "email")
    private String email;

    @JsonIgnore
    @NotNull
    @Length(min = 12, max = 72)
    @Column(name = "password")
    private String hashedPassword;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "is_verified")
    private boolean isVerified;

    @Column(name = "is_admin")
    private boolean isAdmin;

    public Account() {
        // Do nothing
    }

    public Account(String email, String password) {
        this(0, email, password, false, false);
    }

    public Account(int id, String email, String password) {
        this(id, email, password, false);
    }

    public Account(int id, String email, String password, boolean isVerified) {
        this(id, email, password, isVerified, false);
    }

    public Account(int id, String email, String password, boolean isVerified, boolean isAdmin) {
        this(id, email, null, password, isVerified, isAdmin);
    }

    public Account(int id, String email, String phoneNumber, String hashedPassword, boolean isVerified, boolean isAdmin) {
        this.id = id;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hashedPassword = hashedPassword;
        this.isVerified = isVerified;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return hashedPassword;
    }

    public void setPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
