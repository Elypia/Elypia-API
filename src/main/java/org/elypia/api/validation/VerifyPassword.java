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

import org.elypia.api.forms.PasswordForm;

import javax.validation.*;
import java.lang.annotation.*;

/**
 * @author seth@elypia.org (Syed Shah)
 */
@Documented
@Constraint(validatedBy = VerifyPassword.Validator.class)
@Target({ ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface VerifyPassword {

    String message() default "passwords provided don't match";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    class Validator implements ConstraintValidator<VerifyPassword, Object> {

        @Override
        public boolean isValid(Object value, ConstraintValidatorContext context) {
            PasswordForm form = (PasswordForm)value;
            return form.getPassword().equals(form.getVerifyPassword());
        }
    }
}
