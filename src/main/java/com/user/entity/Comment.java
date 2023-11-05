package com.user.entity;

import jakarta.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @NotBlank
    @Size(max = 255)
    private String message;

    @NotNull
    private LocalDateTime commentDateTime;

    @ManyToOne
    @JoinColumn(name = "posted_by_user_id")
    private User postedByUser;



    public Comment() {

    }



    public Comment(String message, LocalDateTime now, User fromUser) {
        this.message=message;
        this.commentDateTime=now;
        this.postedByUser=fromUser;
    }



    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCommentDateTime() {
        return commentDateTime;
    }

    public void setCommentDateTime(LocalDateTime commentDateTime) {
        this.commentDateTime = commentDateTime;
    }

    public User getPostedByUser() {
        return postedByUser;
    }

    public void setPostedByUser(User postedByUser) {
        this.postedByUser = postedByUser;
    }
}

