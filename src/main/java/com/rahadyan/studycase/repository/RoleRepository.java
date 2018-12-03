package com.rahadyan.studycase.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rahadyan.studycase.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	Role findByRole(String role);
}
