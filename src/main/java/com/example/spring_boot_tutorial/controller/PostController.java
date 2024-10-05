package com.example.spring_boot_tutorial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_boot_tutorial.payload.PostDTO;
import com.example.spring_boot_tutorial.security.UserDetailsImpl;
import com.example.spring_boot_tutorial.service.impl.PostServiceImpl;
import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostServiceImpl postServiceImpl;


    @GetMapping("/{id}")
    public ResponseEntity<?> getPost(@PathVariable Long id){
        PostDTO postDTO = postServiceImpl.getByIdServ(id);

        return new ResponseEntity<>(postDTO,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllPosts(){
        List<PostDTO> posts = postServiceImpl.getAllPostsServ();

        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createPost(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
    @RequestBody PostDTO postDTO
    ){
        String response = postServiceImpl.createPost(userDetailsImpl, postDTO);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id,
    @RequestBody PostDTO postDTO
    ){
        String response = postServiceImpl.patchPost(id, postDTO);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id){
        String response = postServiceImpl.deletePost(id);
        return ResponseEntity.ok().body(response);
    }

}
