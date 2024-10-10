package com.example.spring_boot_tutorial.service.impl;

import java.util.List;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.spring_boot_tutorial.entity.Post;
import com.example.spring_boot_tutorial.entity.User;
import com.example.spring_boot_tutorial.exception.ResourceNotFoundException;
import com.example.spring_boot_tutorial.exception.UserDoesNotExistsException;
import com.example.spring_boot_tutorial.payload.PostDTO;
import com.example.spring_boot_tutorial.repository.PostRepository;
import com.example.spring_boot_tutorial.repository.UserRepository;
import com.example.spring_boot_tutorial.security.UserDetailsImpl;
import com.example.spring_boot_tutorial.service.PostService;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public PostDTO getByIdServ(Long id) {
        Post post = postRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Post", "postId", id));

        return alternateToDTO(post);
    }

    @Override
    public List<PostDTO> getAllPostsServ() {
        List<Post> posts = postRepository.findAll();
        List<PostDTO> postDTOs = posts.stream()
        .map(
            post -> alternateToDTO(post)
        ).toList();

        return postDTOs;
    }

    private PostDTO alternateToDTO(Post post){
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setText(post.getText());
        postDTO.setUser(post.getUser());
        postDTO.setCreatedDate(post.getCreatedDate());
        postDTO.setUpdatedDate(post.getUpdatedDate());

        return postDTO;
    }

    @Override
    public List<PostDTO> getByUserIdServ(Authentication authentication) {
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
        UUID userId = userDetailsImpl.getId();
        User user = userRepository.findById(userId)
        .orElseThrow(
            () -> new UserDoesNotExistsException(userId)
        );

        List<Post> posts = postRepository.findByUser(user);
        List<PostDTO> postDTOs = posts.stream().map(
            post -> alternateToDTO(post)
        ).toList();

        return postDTOs;
    }

    @Override
    public String createPost(Authentication authentication,PostDTO postDTO) {
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
        UUID userId = userDetailsImpl.getId();
        User user = userRepository.findById(userId)
        .orElseThrow(
            () -> new UserDoesNotExistsException(userId)
        );

        Post post = new Post();
        post.setText(postDTO.getText());
        post.setUser(user);
        postRepository.save(post);

        return "Create post successfully.";
    }

    @Override
    public String patchPost(Long id, PostDTO postDTO) {
        Post post = postRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Post", "id", id)
        );

        post.setText(postDTO.getText());
        postRepository.save(post);

        return "Update post successfully.";
    }

    @Override
    public String deletePost(Long id) {
        postRepository.deleteById(id);

        return "Delete post successfully.";
    }
    
}
