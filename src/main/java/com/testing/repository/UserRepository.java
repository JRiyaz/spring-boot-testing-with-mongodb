package com.testing.repository;

import com.testing.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    @Query("{ 'id': ?0, 'email': 1? }")
    Optional<User> findByIdAndEmail(String id, String email);
}
