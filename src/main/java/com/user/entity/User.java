package com.user.entity;

import jakarta.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="usercomment")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank
    @Size(max = 25)
    private String commentFrom;

    public User(String commentFrom, String commentFrom1) {
        this.commentFrom=commentFrom;
        this.commentTo=commentFrom1;
    }

    public User() {

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCommentFrom() {
        return commentFrom;
    }

    public void setCommentFrom(String commentFrom) {
        this.commentFrom = commentFrom;
    }

    public String getCommentTo() {
        return commentTo;
    }

    public void setCommentTo(String commentTo) {
        this.commentTo = commentTo;
    }

    @NotBlank
    @Size(max = 25)
    private String commentTo;


}

