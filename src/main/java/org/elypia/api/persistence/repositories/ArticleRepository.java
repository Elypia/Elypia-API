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

package org.elypia.api.persistence.repositories;

import org.elypia.api.persistence.entities.Article;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author seth@elypia.org (Syed Shah)
 */
@Repository
public interface ArticleRepository extends CrudRepository<Article, Integer> {

    Article findById(int id);

    @EntityGraph(attributePaths = "tags")
    List<Article> findTop3ByOrderByCreatedDateDesc();
}
