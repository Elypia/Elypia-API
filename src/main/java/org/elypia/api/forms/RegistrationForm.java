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

package org.elypia.api.forms;

import org.elypia.api.validation.EmailNotTaken;

import javax.validation.constraints.*;

/**
 * This form is received from the frontend when the user
 * attempts to sign up for an Elypia account.
 *
 * @author seth@elypia.org (Syed Shah)
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
