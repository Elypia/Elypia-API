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

import org.elypia.api.forms.ArticleForm;
import org.elypia.api.persistence.entities.*;
import org.elypia.api.persistence.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author seth@elypia.org (Syed Shah)
 */
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
