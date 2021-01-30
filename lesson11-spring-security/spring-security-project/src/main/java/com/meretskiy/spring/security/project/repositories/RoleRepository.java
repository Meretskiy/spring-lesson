package com.meretskiy.spring.security.project.repositories;

import com.meretskiy.spring.security.project.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
