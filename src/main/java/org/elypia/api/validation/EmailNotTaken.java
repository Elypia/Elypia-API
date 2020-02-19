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
