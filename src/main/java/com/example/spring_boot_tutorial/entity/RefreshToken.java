package com.example.spring_boot_tutorial.entity;

import java.time.Instant;
import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "refreshtoken_info")
@Entity
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    @Column(name = "refresh_token",unique = true,columnDefinition = "TEXT",length = 512)
    private String token;

    @Column(name = "expiry_date")
    private Instant expiryDate;

    @Column(name = "created_time",updatable = false)
    @CreationTimestamp
    private LocalDateTime createdTime;

    @ColumnDefault("false")
    @Column(name = "is_deleted")
    private Boolean isDeleted;
}
