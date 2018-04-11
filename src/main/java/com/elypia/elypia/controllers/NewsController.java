package com.elypia.elypia.controllers;

import com.elypia.elypia.requests.NewsData;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class NewsController {

    @RequestMapping(value = "/api/news", method = POST)
    public void createArticle(@RequestBody NewsData data) {
        
    }
}
