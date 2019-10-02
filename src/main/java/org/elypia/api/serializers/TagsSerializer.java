/*
 * Elypia API - Backend for Elypia website and core services.
 * Copyright (C) 2019-2019  Elypia CIC
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
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
