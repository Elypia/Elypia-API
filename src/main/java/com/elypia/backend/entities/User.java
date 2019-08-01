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

package com.elypia.backend.entities;

import com.elypia.backend.forms.RegisterForm;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Entity(name = "users")
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "user_password")
    private byte[] password;

    @Column(name = "is_verified")
    private boolean isVerified;

    @Column(name = "is_admin")
    private boolean isAdmin;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "usernames", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "username")
    private List<String> usernames;

    public User() {
        // Do nothing
    }

    public User(RegisterForm form) {
        this(form.getEmail(), form.getPassword());
    }

    public User(String email, String password) {
        this(0, email, password, false, false);
    }

    public User(String email, String password, boolean isVerified, boolean isAdmin) {
        this(0, email, password, isVerified, isAdmin);
    }

    public User(int id, String email, String password, boolean isVerified, boolean isAdmin) {
        this(id, email, null, password.getBytes(StandardCharsets.UTF_8), isVerified, isAdmin);
    }

    public User(int id, String email, String phoneNumber, byte[] password, boolean isVerified, boolean isAdmin) {
        this.id = id;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
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
        return new String(password, StandardCharsets.UTF_8);
    }

    public void setPassword(String password) {
        this.password = password.getBytes(StandardCharsets.UTF_8);
    }

    public void setPassword(byte[] password) {
        this.password = password;
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

    public List<String> getUsernames() {
        return usernames;
    }

    public void setUsername(List<String> usernames) {
        this.usernames = usernames;
    }
}
