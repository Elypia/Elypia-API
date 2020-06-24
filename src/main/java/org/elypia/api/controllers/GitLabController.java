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

import org.elypia.api.services.gitlab.*;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Controller to query information from the GitLab API.
 *
 * @author seth@elypia.org (Seth Falco)
 * @since 1.0.0
 */
@RequestMapping("/gitlab")
@RestController
public class GitLabController {

    private static final Logger logger = LoggerFactory.getLogger(GitLabController.class);

    private final GitLabConfig config;
    private final GitLabService service;

    @Autowired
    public GitLabController(GitLabConfig config, GitLabService service) {
        this.config = config;
        this.service = service;
    }

    @GetMapping("/group")
    public Group getGroup() throws GitLabApiException {
        return service.getGroup(config.getGroupId());
    }

    @GetMapping("/projects")
    public List<Project> getPublicProjects(@RequestParam(name = "projectIds", required = false) List<Integer> projectIds) throws GitLabApiException {
        final int groupId = config.getGroupId();
        List<Project> projects = (projectIds == null || projectIds.isEmpty()) ? service.getPublicProjects(groupId) : service.getProjects(groupId, projectIds);

        return projects.stream()
            .sorted(Comparator.comparingInt(Project::getStarCount))
            .collect(Collectors.toList());
    }
}
