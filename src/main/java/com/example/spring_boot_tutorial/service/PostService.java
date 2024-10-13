package com.example.spring_boot_tutorial.service;
import java.util.List;

import com.example.spring_boot_tutorial.payload.PostDTO;

public interface PostService {
    PostDTO getByIdServ(Long id);

    List<PostDTO> getAllPostsServ();
    
    List<PostDTO> getByUserIdServ();
    
    String createPost(PostDTO postDTO);

    String patchPost(Long id,PostDTO postDTO);

    String deletePost(Long id);
}
