package com.elypia.backend.database.repositories;

import com.elypia.backend.database.entities.Comment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<Comment, Integer> {

    Comment findCommentById(int id);

    default Slice<Comment> findAllByArticleId(int articleId, int page) {
        var req = PageRequest.of(page, 20, Sort.Direction.DESC, "createdDate");
        return findAllByArticleId(articleId, req);
    }

    Slice<Comment> findAllByArticleId(int articleId, Pageable pageable);
}
