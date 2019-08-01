/*
 * Copyright (C) 2019  Elypia
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.elypia.backend.controllers;

import com.elypia.backend.entities.Comment;
import com.elypia.backend.forms.CommentForm;
import com.elypia.backend.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

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
        Comment comment = new Comment(form);
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
