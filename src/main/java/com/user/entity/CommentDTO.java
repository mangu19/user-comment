package com.user.entity;

public class CommentDTO {
    private String message;
    private Long postedByUserId;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getPostedByUserId() {
        return this.postedByUserId;
    }

    public void setPostedByUserId(Long postedByUserId) {
        this.postedByUserId = postedByUserId;
    }

    public CommentDTO(String message, Long postedByUserId) {
        this.message = message;
        this.postedByUserId = postedByUserId;
    }

    // Getters and setters
}


