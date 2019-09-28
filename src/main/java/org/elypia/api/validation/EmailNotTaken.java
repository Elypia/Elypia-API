/*
 * Elypia API - Backend for Elypia website and core services.
 * Copyright (C) 2019-2019  Elypia CIC
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

package org.elypia.api.validation;

import org.elypia.api.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.*;
import java.lang.annotation.*;

/**
 * @author seth@elypia.org (Syed Shah)
 */
@Documented
@Constraint(validatedBy = EmailNotTaken.Validator.class)
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailNotTaken {

    String message() default "email has been used by another";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    class Validator implements ConstraintValidator<EmailNotTaken, String> {

        private AccountService accountService;

        @Autowired
        public Validator(AccountService accountService) {
            this.accountService = accountService;
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            return accountService.emailExists(value);
        }
    }
}
