package com.example.spring_boot_tutorial.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_boot_tutorial.entity.Post;
import com.example.spring_boot_tutorial.entity.User;
import java.util.Date;


@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findByUser(User user);

    List<Post> findByTextContaining(String text);

    List<Post> findByCreatedDateAfter(Date createdDate);

    List<Post> findByUpdatedDateBefore(Date updatedDate);
}
