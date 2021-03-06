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

import org.elypia.api.persistence.entities.Comment;
import org.springframework.data.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author seth@elypia.org (Syed Shah)
 */
@Repository
public interface CommentRepository extends PagingAndSortingRepository<Comment, Integer> {

    Comment findCommentById(int id);

    default Slice<Comment> findAllByArticleId(int articleId, int page) {
        var req = PageRequest.of(page, 20, Sort.Direction.DESC, "createdDate");
        return findAllByArticleId(articleId, req);
    }

    Slice<Comment> findAllByArticleId(int articleId, Pageable pageable);
}
