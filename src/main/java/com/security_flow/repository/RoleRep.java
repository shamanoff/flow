package com.security_flow.repository;


import com.security_flow.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRep extends JpaRepository<Roles, Long> {
}
