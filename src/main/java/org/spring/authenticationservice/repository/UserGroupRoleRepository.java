package org.spring.authenticationservice.repository;

import org.spring.authenticationservice.model.Group;
import org.spring.authenticationservice.model.User;
import org.spring.authenticationservice.model.UserGroupRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserGroupRoleRepository extends JpaRepository<UserGroupRole,Long> {
    boolean existsByUserAndGroup( User user, Group group);
    Optional<UserGroupRole> findByUserIdAndGroupIdAndStatus(Long userId, Long groupId, UserGroupRole.InvitationStatus status);
    List<UserGroupRole> findByGroupAndStatus(Group group, UserGroupRole.InvitationStatus status);

}

