/*
 * Copyright (C) 2019  Elypia CIC
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

package org.elypia.api.controllers;

import org.elypia.api.entities.*;
import org.elypia.api.forms.ArticleForm;
import org.elypia.api.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
        List<ArticleTag> tags = form.getTags().stream().map(ArticleTag::new).collect(Collectors.toList());
        Article post = new Article(form.getTitle(), form.getContent(), tags);
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
