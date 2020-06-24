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

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("gitlab")
public class GitLabConfig {

    /** The default host for this configuration, which points to the public instance. */
    private static final String PUBLIC_HOST = "https://gitlab.com";

    /** The host to query the API from. */
    private String host;

    /** The personal access token of a user on the host. */
    private String personalAccessToken;

    /** The group ID of the owner of this instance. */
    private int groupId;

    public GitLabConfig() {
        this.host = PUBLIC_HOST;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPersonalAccessToken() {
        return personalAccessToken;
    }

    public void setPersonalAccessToken(String personalAccessToken) {
        this.personalAccessToken = personalAccessToken;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
