package com.example.spring_boot_tutorial.entity;


import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comment_info")
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text",nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(
        name = "user_id",
        foreignKey = @ForeignKey(name="fk_user_id")
    )
    private User user;

    @ManyToOne
    @JoinColumn(
        name = "post_id",
        foreignKey = @ForeignKey(name = "fk_post_id")
    )
    private Post post;

    @CreationTimestamp
    @Column(name = "created_timestamp",updatable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(name = "updated_timestamp",updatable = true)
    private LocalDateTime updatedDate;
}
