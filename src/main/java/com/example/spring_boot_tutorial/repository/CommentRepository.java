package com.example.spring_boot_tutorial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_boot_tutorial.entity.Comment;
import com.example.spring_boot_tutorial.entity.Post;
import com.example.spring_boot_tutorial.entity.User;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByUser(User user);
    
    List<Comment> findByPost(Post post);

    List<Comment> findByUserAndPost(User user, Post post);

    List<Comment> findByTextContaining(String text);

}
