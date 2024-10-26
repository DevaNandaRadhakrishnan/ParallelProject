package com.example.auth_service.repository;

import com.example.auth_service.entity.UserCredentialsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCredentialsRepository extends MongoRepository<UserCredentialsEntity, String> {
    Optional<UserCredentialsEntity> findByName(String name);
}
