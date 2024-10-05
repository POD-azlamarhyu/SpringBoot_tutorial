package com.example.spring_boot_tutorial.service;
import java.util.List;
import java.util.UUID;
import com.example.spring_boot_tutorial.payload.PostDTO;
import com.example.spring_boot_tutorial.security.UserDetailsImpl;

public interface PostService {
    PostDTO getByIdServ(Long id);

    List<PostDTO> getAllPostsServ();
    
    List<PostDTO> getByUserIdServ(UUID userId);
    
    String createPost(UserDetailsImpl userDetailsImpl,PostDTO postDTO);

    String patchPost(Long id,PostDTO postDTO);

    String deletePost(Long id);
}
