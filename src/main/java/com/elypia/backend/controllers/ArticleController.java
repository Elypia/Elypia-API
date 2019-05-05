package com.elypia.backend.controllers;

import com.elypia.backend.database.entities.Article;
import com.elypia.backend.database.repositories.ArticleRepository;
import com.elypia.backend.forms.ArticleForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/articles")
@RestController
public class ArticleController {

    private ArticleRepository articleRepo;

    @Autowired
    public ArticleController(ArticleRepository articleRepo) {
        this.articleRepo = articleRepo;
    }

    @PostMapping
    public Article createPost(@RequestBody ArticleForm form) {
        Article post = new Article(form);
        return articleRepo.save(post);
    }

    @GetMapping
    public Article getPostById(@RequestParam int articleId) {
        return articleRepo.findById(articleId);
    }

    @DeleteMapping
    public void removePost(@RequestParam int articleId) {
        articleRepo.deleteById(articleId);
    }

    @GetMapping("/recent")
    public List<Article> getRecentPosts() {
        return articleRepo.findTop3ByOrderByCreatedDateDesc();
    }
}
