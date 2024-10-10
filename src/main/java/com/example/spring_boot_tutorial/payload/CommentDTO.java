package com.example.spring_boot_tutorial.payload;

import com.example.spring_boot_tutorial.entity.Post;
import com.example.spring_boot_tutorial.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Long id;
    private String text;
    private User user;
    private Post post;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
