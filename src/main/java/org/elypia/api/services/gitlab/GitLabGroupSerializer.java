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
import org.gitlab4j.api.models.Group;

import java.io.IOException;

/**
 * @author seth@elypia.org (Seth Falco)
 * @since 1.0.0
 */
public class GitLabGroupSerializer extends StdSerializer<Group> {

    public GitLabGroupSerializer() {
        super(Group.class);
    }

    /**
     * @param group
     * @param gen
     * @param serializers
     */
    @Override
    public void serialize(Group group, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("id", group.getId());
        gen.writeStringField("name", group.getName());
        gen.writeStringField("url", group.getWebUrl());
        gen.writeStringField("fullPath", group.getFullPath());

        gen.writeArrayFieldStart("projects");
        gen.writeObject(group.getProjects());
        gen.writeEndArray();

        gen.writeEndObject();
    }
}
