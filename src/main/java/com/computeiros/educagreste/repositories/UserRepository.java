package com.computeiros.educagreste.repositories;

import com.computeiros.educagreste.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository <UserEntity, Long > {
    UserEntity findByEmail(String email);
}