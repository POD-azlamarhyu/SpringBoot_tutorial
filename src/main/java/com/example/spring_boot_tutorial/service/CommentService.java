package com.example.spring_boot_tutorial.service;

import java.util.List;

import com.example.spring_boot_tutorial.entity.Comment;
import com.example.spring_boot_tutorial.payload.CommentDTO;

public interface CommentService {
    
    String createCommentServ(Long postId, CommentDTO commentDTO);

    List<CommentDTO> findAllCommentServ();

    Comment findByIdServ(Long id);

    List<CommentDTO> findByPostIdServ(Long postId);

    List<CommentDTO> findByUserIdServ();

    String patchCommentServ(Long id,CommentDTO commentDTO);

    String deleteCommentServ(Long id);
}
