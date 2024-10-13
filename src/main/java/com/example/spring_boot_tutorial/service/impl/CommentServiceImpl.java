package com.example.spring_boot_tutorial.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.spring_boot_tutorial.entity.Comment;
import com.example.spring_boot_tutorial.entity.Post;
import com.example.spring_boot_tutorial.entity.User;
import com.example.spring_boot_tutorial.exception.ResourceNotFoundException;
import com.example.spring_boot_tutorial.exception.UserDoesNotExistsException;
import com.example.spring_boot_tutorial.payload.CommentDTO;
import com.example.spring_boot_tutorial.repository.CommentRepository;
import com.example.spring_boot_tutorial.repository.PostRepository;
import com.example.spring_boot_tutorial.repository.UserRepository;
import com.example.spring_boot_tutorial.security.UserDetailsImpl;
import com.example.spring_boot_tutorial.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;


    @Override
    public String createCommentServ(Long postId, CommentDTO commentDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
        UUID userId = userDetailsImpl.getId();
        User user = userRepository.findById(userId).orElseThrow(
            () -> new UserDoesNotExistsException(userId)
        );
        
        Post post = postRepository.findById(postId).orElseThrow(
            () -> new ResourceNotFoundException("Comment", "id", postId)
        );
        
        Comment comment = new Comment();
        comment.setText(commentDTO.getText());
        comment.setPost(post);
        comment.setUser(user);
        commentRepository.save(comment);

        return "create comment successfully.";
    }

    @Override
    public List<CommentDTO> findAllCommentServ() {
        List<Comment> comments = commentRepository.findAll();
        List<CommentDTO> commentDTOs = comments.stream().map(
            comment -> alternateCommentDTO(comment)
        ).toList();
        
        return commentDTOs;
    }

    @Override
    public Comment findByIdServ(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Comment", "id", id)
        );

        return comment;
    }

    @Override
    public List<CommentDTO> findByPostIdServ(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
            () -> new ResourceNotFoundException(null, null, postId)
        );
        List<Comment> comments = commentRepository.findByPost(post);
        List<CommentDTO> commentDTOs = comments.stream().map(
            comment -> alternateCommentDTO(comment)
        ).toList();
        return commentDTOs;
    }

    @Override
    public List<CommentDTO> findByUserIdServ() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
        UUID userId = userDetailsImpl.getId();
        User user = userRepository.findById(userId).orElseThrow(
            () -> new UserDoesNotExistsException(userId)
        );

        List<Comment> comments = commentRepository.findByUser(user);
        List<CommentDTO> commentDTOs = comments.stream().map(
            comment -> alternateCommentDTO(comment)
        ).toList();
        return commentDTOs;
    }

    private CommentDTO alternateCommentDTO(Comment comment){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setText(commentDTO.getText());
        commentDTO.setUser(comment.getUser());
        commentDTO.setPost(comment.getPost());
        commentDTO.setCreatedDate(comment.getCreatedDate());
        commentDTO.setUpdatedDate(comment.getUpdatedDate());

        return commentDTO;
    }

    @Override
    public String patchCommentServ(Long id, CommentDTO commentDTO) {
        Comment comment = commentRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Comment", "id", id)
        );
        comment.setText(commentDTO.getText());
        commentRepository.save(comment);

        return "update Comment successfully";
    }

    @Override
    public String deleteCommentServ(Long id) {
        commentRepository.deleteById(id);
        
        return "delete comment successfully.";
    }
    
}
