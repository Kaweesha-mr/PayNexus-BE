package org.spring.authenticationservice.repository;

import org.spring.authenticationservice.model.Group;
import org.spring.authenticationservice.model.User;
import org.spring.authenticationservice.model.UserGroupRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGroupRoleRepository extends JpaRepository<UserGroupRole,Long> {
    boolean existsByUserAndGroup( User user, Group group);
}
