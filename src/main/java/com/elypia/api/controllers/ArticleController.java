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

package com.elypia.api.controllers;

import com.elypia.api.entities.Article;
import com.elypia.api.forms.ArticleForm;
import com.elypia.api.repositories.ArticleRepository;
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
