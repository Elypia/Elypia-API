package com.elypia.backend.database.entities;

import com.elypia.backend.forms.CommentForm;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "comments")
@Table
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private int id;

    @Column(name = "article_id")
    private int articleId;

    @Column(name = "parent_comment_id")
    private Integer parentCommentId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "content")
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date createdDate;

    public Comment() {
        // Do nothing
    }

    public Comment(CommentForm form) {
        this.articleId = form.getArticleId();
        this.parentCommentId = form.getParentCommentId();
        this.content = form.getContent();
    }

    public Comment(int articleId) {
        this.articleId = articleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getParentCommentId() {
        return parentCommentId == null ? 0 : parentCommentId;
    }

    public void setParentCommentId(Integer parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
