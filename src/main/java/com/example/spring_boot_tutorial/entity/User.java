package com.example.spring_boot_tutorial.entity;


import java.util.List;
import java.util.Set;
import java.util.UUID;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_info")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "user_name",nullable = false)
    private String username;
    @Column(name = "email",nullable = false,unique = true)
    private String email;
    @Column(name = "login_id",nullable = true,unique = true)
    private String loginId;
    @Column(name = "password",nullable = false)
    private String password;
    
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Post> posts;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Comment> comments;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
        name = "user_role_info",
        joinColumns = @JoinColumn(name="user_id",referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name="role_id",referencedColumnName = "id")
    )
    private Set<Role> role;
}
