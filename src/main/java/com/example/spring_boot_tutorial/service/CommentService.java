package com.example.spring_boot_tutorial.service;

import java.util.List;
import java.util.UUID;

import com.example.spring_boot_tutorial.entity.Comment;
import com.example.spring_boot_tutorial.payload.CommentDTO;

public interface CommentService {
    

    String createCommentServ(CommentDTO commentDTO);

    List<Comment> findAllCommentServ();

    List<Comment> findByIdServ(Long id);

    List<Comment> findByPostIdServ(Long postId);

    List<Comment> findByUserIdServ(UUID userId);

    String patchCommentServ(Long id,CommentDTO commentDTO);

    String deleteCommentServ(Long id);
}
