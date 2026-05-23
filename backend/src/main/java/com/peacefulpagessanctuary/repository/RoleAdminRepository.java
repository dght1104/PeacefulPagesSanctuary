package com.peacefulpagessanctuary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peacefulpagessanctuary.model.RoleAdmin;

import java.util.Optional;

public interface RoleAdminRepository extends JpaRepository<RoleAdmin, Long> {

    Optional<RoleAdmin> findByRoleName(String roleName);
}