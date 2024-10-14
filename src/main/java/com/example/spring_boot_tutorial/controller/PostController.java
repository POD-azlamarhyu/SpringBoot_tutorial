package com.example.spring_boot_tutorial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_boot_tutorial.payload.PostDTO;
import com.example.spring_boot_tutorial.service.PostService;
import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getPost(@PathVariable Long id){
        PostDTO postDTO = postService.getByIdServ(id);

        return new ResponseEntity<>(postDTO,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllPosts(){
        List<PostDTO> posts = postService.getAllPostsServ();

        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostDTO postDTO){
        System.out.println("\n処理が走っています．\n");
        String response = postService.createPost(postDTO);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id,
    @RequestBody PostDTO postDTO
    ){
        String response = postService.patchPost(id, postDTO);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id){
        String response = postService.deletePost(id);
        return ResponseEntity.ok().body(response);
    }

}
