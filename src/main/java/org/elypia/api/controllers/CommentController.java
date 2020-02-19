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

import org.elypia.api.entities.Comment;
import org.elypia.api.forms.CommentForm;
import org.elypia.api.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

/**
 * @author seth@elypia.org (Syed Shah)
 */
@RequestMapping("/api/comments")
@RestController
public class CommentController {

    private CommentRepository commentRepo;

    @Autowired
    public CommentController(CommentRepository commentRepo) {
        this.commentRepo = commentRepo;
    }

    @PostMapping
    public Comment createComment(@RequestBody CommentForm form) {
        Comment comment = new Comment(form.getArticleId(), form.getParentCommentId(), form.getContent());
        return commentRepo.save(comment);
    }

    @GetMapping
    public Comment getCommentsByArticle(@RequestParam int articleId) {
        return commentRepo.findCommentById(articleId);
    }

    @DeleteMapping
    public void removeComment(@RequestParam int commentId) {
        commentRepo.deleteById(commentId);
    }

    @GetMapping("/paginate")
    public Slice<Comment> getCommentsByArticle(@RequestParam int articleId, @RequestParam(defaultValue = "0") int page) {
        return commentRepo.findAllByArticleId(articleId, page);
    }
}
