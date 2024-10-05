package com.example.spring_boot_tutorial.payload;

import com.example.spring_boot_tutorial.entity.User;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private Long id;
    private String text;
    private User user;
    private Date createdDate;
    private Date updatedDate;
}
