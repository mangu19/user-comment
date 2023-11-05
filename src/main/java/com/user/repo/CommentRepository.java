package com.user.repo;

import com.user.entity.Comment;
import com.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostedByUser(User user);
}

