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
