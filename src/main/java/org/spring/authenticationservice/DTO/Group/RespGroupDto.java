package org.spring.authenticationservice.DTO.Group;

import org.spring.authenticationservice.DTO.Members.GrpMember;
import org.spring.authenticationservice.DTO.Members.GrpMemberResp;
import org.spring.authenticationservice.model.User;

import java.util.List;

public class RespGroupDto {
    private Long id;
    private String name;
    private Float ContributionAmount;
    private Integer memberCount;
    private Long adminName;
    private List<GrpMemberResp> members;
    private Integer CurrentCycle;

    public Integer getCurrentCycle() {
        return CurrentCycle;
    }

    public void setCurrentCycle(Integer currentCycle) {
        CurrentCycle = currentCycle;
    }

    public List<GrpMemberResp> getMembers() {
        return members;
    }

    public void setMembers(List<GrpMemberResp> members) {
        this.members = members;
    }

    public Long getAdminName() {
        return adminName;
    }

    public void setAdminName(Long adminName) {
        this.adminName = adminName;
    }

    public Integer getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
    }

    public Float getContributionAmount() {
        return ContributionAmount;
    }

    public void setContributionAmount(Float contributionAmount) {
        ContributionAmount = contributionAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
