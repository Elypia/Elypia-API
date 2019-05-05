package com.elypia.backend.database.entities;

import javax.persistence.*;

@Entity(name = "upvotes")
@Table
public class Upvote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "upvote_id")
    private int id;

    @Column(name = "comment_id")
    private int commentId;

    @Column(name = "user_id")
    private int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
