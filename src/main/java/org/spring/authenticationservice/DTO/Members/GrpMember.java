package org.spring.authenticationservice.DTO.Members;

public class GrpMember {

    private String username;
    private Long memberId;
    private String role;

    public GrpMember(String username, Long memberId, String role) {
        this.username = username;
        this.memberId = memberId;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
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
