package com.elypia.backend.forms;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class ArticleForm {

    @NonNull
    @NotEmpty
    @Length(min = 1, max = 128)
    private String title;

    @NonNull
    @NotEmpty
    @Length(min = 1, max = 2048)
    private String content;

    private List<String> tags;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
