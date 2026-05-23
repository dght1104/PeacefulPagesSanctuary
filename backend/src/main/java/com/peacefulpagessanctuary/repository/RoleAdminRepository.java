package com.peacefulpagessanctuary.repository;

import com.peacefulpagessanctuary.model.RoleAdmin;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleAdminRepository extends JpaRepository<RoleAdmin, Long> {

    Optional<RoleAdmin> findByRoleName(String roleName);

    boolean existsByRoleName(String roleName);

}