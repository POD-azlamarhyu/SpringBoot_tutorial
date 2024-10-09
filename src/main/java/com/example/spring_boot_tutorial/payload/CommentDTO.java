package com.example.spring_boot_tutorial.payload;

import com.example.spring_boot_tutorial.entity.Post;
import com.example.spring_boot_tutorial.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Long id;
    private String text;
    private Post post;
}
