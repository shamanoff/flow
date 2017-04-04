package com.security_flow.repository;


import com.security_flow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRep extends JpaRepository<User, Long> {
    User findByUsername(String userName);
}
