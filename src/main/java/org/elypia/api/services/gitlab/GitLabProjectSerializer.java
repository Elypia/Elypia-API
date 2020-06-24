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

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.gitlab4j.api.models.*;

import java.io.IOException;

/**
 * @author seth@elypia.org (Seth Falco)
 * @since 1.0.0
 */
public class GitLabProjectSerializer extends StdSerializer<Project> {

    public GitLabProjectSerializer() {
        super(Project.class);
    }

    /**
     * @param project
     * @param gen
     * @param serializers
     */
    @Override
    public void serialize(Project project, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("id", project.getId());
        gen.writeStringField("url", project.getWebUrl());
        gen.writeStringField("avatarUrl", project.getAvatarUrl());
        gen.writeStringField("name", project.getName());
        gen.writeStringField("description", project.getDescription());
        gen.writeBooleanField("isPublic", project.getVisibility() == Visibility.PUBLIC);
        gen.writeNumberField("stars", project.getStarCount());
        gen.writeStringField("createdAt", project.getCreatedAt().toString());
        gen.writeEndObject();
    }
}
