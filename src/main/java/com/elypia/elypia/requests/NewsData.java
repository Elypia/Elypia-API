package com.elypia.elypia.requests;

import javax.validation.constraints.NotEmpty;

public class NewsData {

    @NotEmpty(message = "You must be logged in to post a comment.")
    private String token;

    @NotEmpty(message = "You must specify a title for the news article.")
    private String title;

    private String thumbnail;

    @NotEmpty(message = "You must specify the contents of the news article.")
    private String content;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
