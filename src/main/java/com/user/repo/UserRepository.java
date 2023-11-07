package com.user.repo;

import com.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Long> {
    User findByCommentTo(String commentTo);
    User findByCommentFrom(String commentFrom);
}

