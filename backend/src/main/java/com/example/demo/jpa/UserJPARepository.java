package com.example.demo.jpa;

import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJPARepository extends JpaRepository<UserEntity, Long> {
    UserEntity findAllByEmailAndPassword(String email, String password);

    Optional<UserEntity> findById(Long id);
}
