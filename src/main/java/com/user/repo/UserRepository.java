package com.user.repo;

import com.user.entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Long> {
   List<User> findByCommentTo(String commentTo);
    User findByCommentFrom(String commentFrom);
}

