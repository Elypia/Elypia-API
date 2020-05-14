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

import javax.validation.*;
import javax.validation.constraints.*;
import java.lang.annotation.*;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(ElementType.FIELD)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {})
@NotNull
@Size(min = 32, max = 32)
@Pattern(regexp = "^[sp]k_(?:live|test)_[A-Za-z\\d]{24}$")
public @interface StripeKey {

    /**
     * @return The type of StripeKey this is.
     */
    Type type();

    /**
     * @return the error message template
     */
    String message() default "{org.elypia.api.validation.StripeKey.message}";

    /**
     * @return the groups the constraint belongs to
     */
    Class<?>[] groups() default { };

    /**
     * @return the payload associated to the constraint
     */
    Class<? extends Payload>[] payload() default { };

    class Validator implements ConstraintValidator<StripeKey, String> {

        private Type type;

        @Override
        public void initialize(StripeKey constraintAnnotation) {
            type = constraintAnnotation.type();
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            return value.startsWith("s") == (type == Type.SECRET);
        }
    }

    enum Type {
        SECRET,
        PUBLISHABLE
    }
}
