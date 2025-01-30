package org.spring.authenticationservice.repository;

import org.spring.authenticationservice.model.UserGroupRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGroupRoleRepository extends JpaRepository<UserGroupRole,Long> {
}
