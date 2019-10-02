/*
 * Elypia API - Backend for Elypia website and core services.
 * Copyright (C) 2019-2019  Elypia CIC
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package org.elypia.api.forms;

import org.elypia.api.validation.*;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotEmpty;

/**
 * Specify a new password for an account.
 *
 * @author seth@elypia.org (Syed Shah)
 */
@VerifyPassword
public abstract class PasswordForm {

    /** The password of the user. */
    @NonNull
    @NotEmpty
    @Password
    private String password;

    /**
     * The password verification box, there is no need to
     * apply checks on this as it must be the same as {@link #password}
     * so if that passes the checks, so will this.
     */
    private String verifyPassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }
}
