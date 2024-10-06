package com.example.spring_boot_tutorial.service;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.Authentication;

import com.example.spring_boot_tutorial.payload.PostDTO;

public interface PostService {
    PostDTO getByIdServ(Long id);

    List<PostDTO> getAllPostsServ();
    
    List<PostDTO> getByUserIdServ(UUID userId);
    
    String createPost(Authentication authentication,PostDTO postDTO);

    String patchPost(Long id,PostDTO postDTO);

    String deletePost(Long id);
}
