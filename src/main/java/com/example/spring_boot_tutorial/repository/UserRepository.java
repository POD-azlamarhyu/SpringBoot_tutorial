package com.example.spring_boot_tutorial.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.spring_boot_tutorial.entity.User;
import com.example.spring_boot_tutorial.utils.DataBaseQuery;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);
    Optional<User> findByLoginId(String loginId);
    Optional<User> findByLoginIdOrEmail(String loginId,String email);
    Optional<User> findByUsername(String username);

    Boolean existsByEmail(String email);
    Boolean existsByLoginId(String loginId);

    UUID getUUIDByLoginId(String loginId);

    @Query(value = DataBaseQuery.queryGetUsernameByLoginId,nativeQuery = true)
    String getUsernameByLoginId(String loginId);

    String getUsernameById(UUID id);

    @Query(value = DataBaseQuery.queryGetEmailById,nativeQuery = true)
    String getEmailById(UUID id);

    @Query(value = DataBaseQuery.queryGetEmailByLoginId,nativeQuery = true)
    String getEmailByLoginId(String loginId);

    Optional<User> findByIdAndLoginId(UUID id,String loginId);
}
