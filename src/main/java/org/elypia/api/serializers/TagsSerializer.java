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

package org.elypia.api.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.elypia.api.entities.ArticleTag;

import java.io.IOException;
import java.util.List;

/**
 * Serialize {@link ArticleTag} objects so than after JSON serialization
 * they are just a flat list of names rather than objects.
 *
 * @author seth@elypia.org (Syed Shah)
 */
public class TagsSerializer extends StdSerializer<List<ArticleTag>> {

    public TagsSerializer() {
        this(null);
    }

    protected TagsSerializer(Class<List<ArticleTag>> t) {
        super(t);
    }

    @Override
    public void serialize(List<ArticleTag> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartArray();

        for (ArticleTag articleTag : value)
            gen.writeString(articleTag.getTag());

        gen.writeEndArray();
    }
}
