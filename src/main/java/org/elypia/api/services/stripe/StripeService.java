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

package org.elypia.api.services.stripe;

import com.stripe.Stripe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StripeService {

    private StripeConfig config;

    private final String secretKey;
    private final String publishableKey;

    @Autowired
    public StripeService(StripeConfig config) {
        this.config = config;

        secretKey = config.getSecretKey();
        publishableKey = config.getPublishableKey();

        Stripe.apiKey = secretKey;
    }

    public StripeConfig getConfig() {
        return config;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getPublishableKey() {
        return publishableKey;
    }
}
