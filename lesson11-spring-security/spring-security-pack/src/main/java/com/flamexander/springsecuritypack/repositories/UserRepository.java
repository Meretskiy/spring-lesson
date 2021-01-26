package com.flamexander.springsecuritypack.repositories;

import com.flamexander.springsecuritypack.entities.User;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Profile("dao")
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
