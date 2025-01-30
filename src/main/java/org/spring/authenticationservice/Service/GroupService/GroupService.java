package org.spring.authenticationservice.Service.GroupService;

import org.spring.authenticationservice.DTO.Group.GroupDto;
import org.spring.authenticationservice.model.Group;
import org.spring.authenticationservice.model.User;
import org.spring.authenticationservice.model.UserGroupRole;
import org.spring.authenticationservice.repository.GroupRepository;
import org.spring.authenticationservice.repository.UserGroupRoleRepository;
import org.spring.authenticationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.spring.authenticationservice.Utils.SecurityUtils.getUsername;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserGroupRoleRepository userGroupRoleRepository;

    public Group createGroup(GroupDto groupDto) {

        // Fetch the user who is creating the group
        User adminUser = userRepository.findByEmail(getUsername())
                .orElseThrow(() -> new RuntimeException("Admin user not found"));


        Group newGroup = new Group();
        newGroup.setGrpName(groupDto.getGrpName());
        newGroup.setAdminId(adminUser.getId());
        newGroup.setContributionAmount(groupDto.getContributionAmount());

        newGroup.getMembers().add(adminUser);
        adminUser.getGroups().add(newGroup);
        Group savedGroup = groupRepository.save(newGroup);
        userRepository.save(adminUser);

        UserGroupRole newUserGroupRole = new UserGroupRole();
        newUserGroupRole.setUser(adminUser);
        newUserGroupRole.setGroup(savedGroup);
        newUserGroupRole.setRole("ADMIN");

        userGroupRoleRepository.save(newUserGroupRole);
        return savedGroup;

    }
}
