package com.user.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.stereotype.Service;
import com.user.entity.Comment;
import com.user.entity.User;
import com.user.repo.CommentRepository;
import com.user.repo.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void addComment(String commentFrom, String commentTo, String message) {

        User userFrom = null;
        if ((!isValidUserName(commentFrom) || !isValidUserName(commentTo)) || !isValidMessage(message)) {
            throw new InvalidRequestException("Invalid Request");
        }

        userFrom = userRepository.findByCommentFrom(commentFrom);

        if (userFrom == null) {
            userFrom = new User(commentTo, commentFrom);
            userFrom = userRepository.save(userFrom);
        }

        Comment comment = new Comment(message, LocalDateTime.now(), userFrom);

        commentRepository.save(comment);
    }

    public List<Comment> getComments(String commentTo) {
        List<User> users = null;
        if(isValidUserName(commentTo))
            users = userRepository.findByCommentTo(commentTo);
        else
            throw new InvalidRequestException("Invalid Request");

        List<Comment> comments = new ArrayList<>();
        if (users != null) {
            for (User user : users) {
                List<Comment> userComments = commentRepository.findByPostedByUser(user);
                comments.addAll(userComments);
            }
        } else {
            throw new InvalidRequestException("Invalid Request");
        }
        return comments;
    }

    private boolean isValidUserName(String userName) {
        return !userName.isEmpty() && userName.matches("^[a-zA-Z]+$");
    }

    private boolean isValidMessage(String message) {
        return !message.isEmpty();
    }
}
