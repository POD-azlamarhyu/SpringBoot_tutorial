package com.example.spring_boot_tutorial.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_boot_tutorial.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findByUserId(UUID userId);
}
