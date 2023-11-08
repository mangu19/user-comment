package com.user.controller;

import com.user.entity.Comment;
import com.user.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    public ResponseEntity<String> addComment(@RequestParam String commentFrom,
            @RequestParam String commentTo,
            @RequestParam String message) {

        try {
            commentService.addComment(commentFrom, commentTo, message);
            return ResponseEntity.ok("Comment added successfully");
        } catch (InvalidRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Request");
        }
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getComments(@RequestParam String commentTo) {
        List<Comment> comments = null;
        try {
            comments = commentService.getComments(commentTo);
            return ResponseEntity.ok(comments);

        } catch (InvalidRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Request");
        }

    }
}
