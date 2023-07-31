package com.JWT_S3_Security.JWT_S3_Security_Study.repository;

import com.JWT_S3_Security.JWT_S3_Security_Study.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> , JpaSpecificationExecutor {

    Optional<User> findByUserId(String username);
}
