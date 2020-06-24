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

import org.elypia.api.services.companieshouse.*;
import org.elypia.elypiai.companieshouse.models.RegisteredOfficeAddress;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

/**
 * Controller to query information about the company.
 * This is hard-coded to only allow data to be returned to the company
 * of the owner of this backend, it can not be used to return data for
 * any other company to avoid usage as a proxy to official Companies House API.
 *
 * @author seth@elypia.org (Seth Falco)
 * @since 1.0.0
 */
@RequestMapping("/company")
@RestController
public class CompanyController {

    private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

    private final CompaniesHouseConfig config;
    private final CompaniesHouseService service;

    @Autowired
    public CompanyController(CompaniesHouseConfig config, CompaniesHouseService service) {
        this.config = config;
        this.service = service;
    }

    @GetMapping("/address")
    public RegisteredOfficeAddress getPublicProjects() throws ExecutionException, InterruptedException {
        return service.getRegisteredOffice(config.getCompanyNumber()).get();
    }
}
