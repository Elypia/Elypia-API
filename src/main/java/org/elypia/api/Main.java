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

package org.elypia.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.elypia.api.services.companieshouse.CompaniesHouseConfig;
import org.elypia.api.services.gitlab.*;
import org.elypia.api.services.recaptcha.ReCaptchaConfig;
import org.elypia.api.services.stripe.StripeConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

/**
 * @author seth@elypia.org (Syed Shah)
 */
@EnableConfigurationProperties({
    CompaniesHouseConfig.class,
    GitLabConfig.class,
    ReCaptchaConfig.class,
    StripeConfig.class,
})
@EnableAsync
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public Validator getLocalValidatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(new GitLabGroupSerializer());
        module.addSerializer(new GitLabProjectSerializer());
        mapper.registerModule(module);
        return mapper;
    }
}
