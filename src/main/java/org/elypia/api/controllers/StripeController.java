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

package org.elypia.api.controllers;

import com.stripe.exception.StripeException;
import com.stripe.model.*;
import org.elypia.api.forms.ChargeForm;
import org.elypia.api.services.stripe.StripeService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class StripeController {

    private static final Logger logger = LoggerFactory.getLogger(StripeController.class);

    private static final long SECOND_MILLI = TimeUnit.MILLISECONDS.convert(1, TimeUnit.SECONDS);
    private static final long THIRTY_ONE_DAYS_SECONDS = TimeUnit.SECONDS.convert(31, TimeUnit.DAYS);

    /**
     * We {@link Autowired autowire} this so to verify it's been
     * instantiated, but we don't use it.
     */
    private final StripeService service;

    @Autowired
    public StripeController(StripeService service) {
        this.service = service;
    }

    @PostMapping("/charge")
    public Object charge(@Valid @RequestBody ChargeForm form) throws StripeException {
        Map<String, Object> params = Map.of(
            "amount", form.getAmount(),
            "currency", form.getCurrency(),
            "description", form.getDescription(),
            "source", form.getSource()
        );

        Charge charge = Charge.create(params);
        logger.info("Successfully charged: {}", charge);
        return "{\"status\": \"success\"}";
    }

    /**
     * @return Income earned through Stripe in the past 31 days,
     * or the last 100 charges.
     */
    @GetMapping("/previous")
    public Object getMonthsCharges() throws StripeException {
        Map<String, Object> created = Map.of(
            "gte", (System.currentTimeMillis() / SECOND_MILLI) - THIRTY_ONE_DAYS_SECONDS
        );

        Map<String, Object> params = Map.of(
            "created", created,
            "limit", 100
        );

        ChargeCollection collection = Charge.list(params);
        var iter = collection.getData().parallelStream().map(Charge::getAmount).iterator();

        double amount = 0;

        while (iter.hasNext())
            amount += iter.next();

        return "{\"total\":" + (amount / 100.00) + "}";
    }
}
