package org.spring.authenticationservice.DTO.Members;

import org.spring.authenticationservice.model.Role;

import java.util.List;

public class GrpMemberResp {

    private String username;
    private Long memberId;
    private List<Role> role;

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    public GrpMemberResp(String username, Long memberId, List<Role> role) {
        this.username = username;
        this.memberId = memberId;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}
