package com.elypia.backend.controllers;

import com.elypia.backend.database.entities.Comment;
import com.elypia.backend.database.repositories.CommentRepository;
import com.elypia.backend.forms.CommentForm;
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
