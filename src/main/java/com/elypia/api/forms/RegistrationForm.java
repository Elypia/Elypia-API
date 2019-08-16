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

package com.elypia.api.forms;

import com.elypia.api.validation.EmailNotTaken;

import javax.validation.constraints.*;

/**
 * This form is received from the frontend when the user
 * attempts to sign up for an Elypia account.
 */
public class RegistrationForm extends PasswordForm {

    /** The email address that the user is signing up with. */
    @NotNull
    @NotEmpty
    @Email
    @EmailNotTaken
    private String email;

    /** The user must have accepted terms of service, privacy policy, and cookies. */
    @AssertTrue
    private boolean acceptsTerms;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAcceptsTerms() {
        return acceptsTerms;
    }

    public void setAcceptsTerms(boolean acceptsTerms) {
        this.acceptsTerms = acceptsTerms;
    }
}
