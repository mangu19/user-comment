package com.user.controller;

import com.user.entity.Comment;
import com.user.entity.User;
import com.user.repo.CommentRepository;
import com.user.repo.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Transactional
    @PostMapping("/add")
    public ResponseEntity<String> addComment(
            @RequestParam @Pattern(regexp = "^[a-zA-Z ]+$", message = "commentFrom should contain only letters and spaces") String commentFrom,
            @RequestParam @Pattern(regexp = "^[a-zA-Z ]+$", message = "commentTo should contain only letters and spaces") String commentTo,
            @RequestParam @NotBlank(message = "Message cannot be blank") @Size(max = 255, message = "Message should be at most 255 characters long") String message) {
        System.out.println("commFrom="+commentFrom+"\nCommTO="+commentTo+"\nMSG="+message);
        try {
            User userTo=new User();
            userTo.setCommentFrom(commentFrom);
            userTo.setCommentTo(commentTo);
            User userFrom=new User();
            userFrom.setCommentFrom(commentFrom);
            userFrom.setCommentTo(commentTo);
            User toUser = userRepository.findByCommentTo(commentTo).orElseGet(() -> userRepository.save(userTo));

            User fromUser = userRepository.findByCommentTo(commentFrom).orElseGet(() -> userRepository.save(userFrom));
        
            // Optional<User> existingFroUser = userRepository.findByCommentTo(commentTo);
            // User fromUser=null;
            // if (existingFroUser.isPresent()) {
            //     fromUser = existingFroUser.get();
            // } else {
            //     fromUser=userRepository.save(userfrom);
            // }
            //  Optional<User> existingToUser = userRepository.findByCommentTo(commentTo);

            // if (existingToUser.isPresent()) {
            //     User toUser = existingToUser.get();
            // } else {
            //     userRepository.save(userfrom);
            // }
           // User fromUser = userRepository.findByCommentTo(commentFrom).orElseGet(() -> userRepository.save(new User(commentFrom, commentTo)));
            //User toUser = userRepository.findByCommentTo(commentTo).orElseGet(() -> userRepository.save(new User(commentFrom, commentTo)));

            Comment comment = new Comment();
                    comment.setMessage(message);
                    comment.setCommentDateTime(LocalDateTime.now());
                    comment.setPostedByUser(fromUser);
            System.out.println("msg="+comment.getMessage()+"\nCommBy="+comment.getPostedByUser());
            System.out.println("commentToString="+comment.toString());
            commentRepository.save(comment);

            return ResponseEntity.ok("Comment added successfully");
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add comment");
        }
    }

    @GetMapping("/get")
    public List<Comment> getComments(@RequestParam String commentTo) {
        System.out.println("commentToString="+commentTo);
        User user = userRepository.findByCommentTo(commentTo).orElse(null);
        //System.out.println("getUserId="+user.getUserId());
        if (user == null) {
            return Collections.emptyList();
        }

        return commentRepository.findByPostedByUser(user);
    }
}

