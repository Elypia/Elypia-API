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

package org.elypia.api.services.gitlab;

import org.gitlab4j.api.*;
import org.gitlab4j.api.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GitLabService implements AutoCloseable {

    private final GitLabConfig gitlabConfig;
    private final GitLabApi gitlab;

    @Autowired
    public GitLabService(GitLabConfig gitlabConfig) {
        this.gitlabConfig = Objects.requireNonNull(gitlabConfig);
        gitlab = new GitLabApi(gitlabConfig.getHost(), gitlabConfig.getPersonalAccessToken());
    }

    public Group getGroup(int groupId) throws GitLabApiException {
        return gitlab.getGroupApi().getGroup(groupId);
    }

    public List<Project> getProjects(int groupId) throws GitLabApiException {
        return getGroup(groupId).getProjects();
    }

    /**
     * Get only the projects for the project IDs defined.
     *
     * @param groupId The ID of the group to get projects from.
     * @param projects A whitelist of project IDs to obtain.
     * @return A list of projects that exist in the whitelist.
     */
    public List<Project> getProjects(int groupId, Collection<Integer> projects) throws GitLabApiException {
        return getProjects(groupId).stream()
            .filter((p) -> projects.contains(p.getId()))
            .collect(Collectors.toList());
    }

    public List<Project> getPublicProjects(int groupId) throws GitLabApiException {
        return getProjects(groupId).stream()
            .filter((p) -> p.getVisibility() == Visibility.PUBLIC)
            .collect(Collectors.toList());
    }

    @PreDestroy
    @Override
    public void close() {
        this.gitlab.close();
    }
}
