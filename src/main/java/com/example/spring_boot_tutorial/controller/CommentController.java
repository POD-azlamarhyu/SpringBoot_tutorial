package com.example.spring_boot_tutorial.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    
    @Autowired
    CommentService commentService;

    @PostMapping("/{postId}")
    public ResponseEntity<?> createComment(
        Authentication authentication,
        @PathVariable Long postId,
        @RequestBody CommentDTO commentDTO
    ){
        String response = commentService.createCommentServ(authentication,postId,commentDTO);

        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getCommentsByPostId(
        @PathVariable Long postId
    ){  
        List<CommentDTO> commentDTOs = commentService.findByPostIdServ();
        return ResponseEntity.ok().body(commentDTOs).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateComment(
        @PathVariable Long id,
        @RequestBody CommentDTO commentDTO
    ){
        String response = commentService.patchCommentServ(id, commentDTO);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(
        @PathVariable Long id
    ){
        String response = commentService.deleteCommentServ(id);
        return ResponseEntity.ok().body(response);
    }
}
