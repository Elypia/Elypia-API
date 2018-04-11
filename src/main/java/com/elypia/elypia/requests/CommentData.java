package com.elypia.elypia.requests;

import javax.validation.constraints.NotEmpty;

public class CommentData {

    @NotEmpty(message = "You must be logged in to post a comment.")
    private String token;

    @NotEmpty(message = "You must specify what news article you are commenting on.")
    private int articleId;

    @NotEmpty(message = "Comment contents can not be empty.")
    private String content;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
