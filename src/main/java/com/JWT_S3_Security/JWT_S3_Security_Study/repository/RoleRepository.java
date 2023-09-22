package com.JWT_S3_Security.JWT_S3_Security_Study.repository;

import com.JWT_S3_Security.JWT_S3_Security_Study.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role , Long> {
    Role findByRoleType(String roleAdmin);
}
