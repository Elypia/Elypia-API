/*
 * Copyright (C) 2019  Elypia
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

package com.elypia.api.entities;

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
 */
@Entity(name = "accounts")
@Table
public class Account {

    @Min(1)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int id;

    @Length(min = 5)
    @NotNull
    @Column(name = "email")
    private String email;

    @Length(min = 8, max = 72)
    @NotNull
    @JsonIgnore
    @Column(name = "passwd")
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

    public Account(String email, String password, boolean isVerified, boolean isAdmin) {
        this(0, email, password, isVerified, isAdmin);
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
