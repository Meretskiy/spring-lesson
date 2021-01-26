package com.flamexander.springsecuritypack.repositories;

import com.flamexander.springsecuritypack.entities.Role;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("dao")
public interface RoleRepository extends CrudRepository<Role, Long> {
}
