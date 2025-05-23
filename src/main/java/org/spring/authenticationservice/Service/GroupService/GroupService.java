package org.spring.authenticationservice.Service.GroupService;

import org.spring.authenticationservice.DTO.Group.CreateGroupDto;
import org.spring.authenticationservice.DTO.Group.RespGroupDto;
import org.spring.authenticationservice.DTO.Members.GrpMember;
import org.spring.authenticationservice.DTO.Members.GrpMemberResp;
import org.spring.authenticationservice.model.Group;
import org.spring.authenticationservice.model.User;
import org.spring.authenticationservice.model.UserGroupRole;
import org.spring.authenticationservice.repository.GroupRepository;
import org.spring.authenticationservice.repository.UserGroupRoleRepository;
import org.spring.authenticationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.spring.authenticationservice.Utils.SecurityUtils.getUsername;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserGroupRoleRepository userGroupRoleRepository;

    public Group createGroup(CreateGroupDto groupDto) {

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

    public String inviteUserToGroup(Long groupId, Long userId) {
        User newUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new RuntimeException("Group not found"));

        //admin validation should be added

        if(userGroupRoleRepository.existsByUserAndGroup(newUser, group)) {
            throw new RuntimeException("User already has group " + group.getGrpName());
        }

        UserGroupRole invitation = new UserGroupRole();
        invitation.setGroup(group);
        invitation.setUser(newUser);
        invitation.setRole("ROLE_MEMBER");
        invitation.setStatus(UserGroupRole.InvitationStatus.PENDING);
        userGroupRoleRepository.save(invitation);

        //email should be sent to user with JWT

        return "Invitation sent to User " + newUser.getEmail();
    }

    public String respondToInvitation(Long userId, Long groupId, boolean isApproved) {

        //should be updated to decode JWT and get the details

        UserGroupRole invitation = userGroupRoleRepository
                .findByUserIdAndGroupIdAndStatus(userId,groupId,UserGroupRole.InvitationStatus.PENDING)
                .orElseThrow(()-> new RuntimeException("Invitation not found"));

        if(isApproved){
            invitation.setStatus(UserGroupRole.InvitationStatus.APPROVED);

            Group group = invitation.getGroup();
            User user = invitation.getUser();
            group.getMembers().add(user);
            groupRepository.save(group);
        }
        else{
            invitation.setStatus(UserGroupRole.InvitationStatus.REJECTED);
        }

        userGroupRoleRepository.save(invitation);
        return isApproved ? "You have joined the group" : "Rejected";
    }

    public List<GrpMember> getMembers(Long groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new RuntimeException("Group not found"));

        List<UserGroupRole> approvedMembers = userGroupRoleRepository.findByGroupAndStatus(group, UserGroupRole.InvitationStatus.APPROVED);

        return approvedMembers.stream()
                .map(userGroupRole -> new GrpMember(userGroupRole.getUser().getEmail(),userGroupRole.getUser().getId(),userGroupRole.getRole()))
                .collect(Collectors.toList());
    }


    public RespGroupDto findGroupById(Long groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new RuntimeException("Group not found"));
        RespGroupDto respGroupDto = new RespGroupDto();

        respGroupDto.setId(group.getId());
        respGroupDto.setName(group.getGrpName());
        respGroupDto.setContributionAmount(group.getContributionAmount());
        respGroupDto.setCurrentCycle(group.getCurrentCycle());
        respGroupDto.setAdminName(group.getAdminId());
        respGroupDto.setMemberCount(group.getMemberLimit());

        List<GrpMemberResp> grpMembers = group.getMembers().stream()
                .map(user -> new GrpMemberResp(user.getEmail(),user.getId(),user.getRoles())).toList();

        respGroupDto.setMembers(grpMembers);
        return respGroupDto;
    }
}
