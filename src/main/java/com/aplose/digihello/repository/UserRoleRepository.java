package com.aplose.digihello.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aplose.digihello.model.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

}
