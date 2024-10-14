package com.example.spring_boot_tutorial.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.spring_boot_tutorial.entity.RefreshToken;
import com.example.spring_boot_tutorial.entity.User;
import com.example.spring_boot_tutorial.utils.DataBaseQuery;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>{
    Optional<RefreshToken> findByToken(String token);

    Optional<RefreshToken> findByUser(User user);

    Optional<RefreshToken> findByUserAndToken(User user,String token);

    List<RefreshToken> findByUserAndTokenOrderByCreatedTimeDesc(User user, String token);

    Optional<RefreshToken> findByUserAndTokenAndIsDeleted(User user,String token,Boolean flag);

    @Query(value = DataBaseQuery.queryFindTokenByUserAndToken,nativeQuery = true)
    RefreshToken findTokenByUserAndToken(User user,String token,Boolean flag);
}
