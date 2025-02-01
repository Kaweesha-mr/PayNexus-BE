package org.spring.authenticationservice.DTO.Group;

import com.fasterxml.jackson.annotation.JsonProperty;

import static org.spring.authenticationservice.Utils.SecurityUtils.getUsername;

public class CreateGroupDto {

    @JsonProperty("name")
    private String GrpName;

    @JsonProperty("contributedAmount")
    private Float contributionAmount;

    @JsonProperty("adminId")
    private String adminId = getUsername();


    public String getGrpName() {
        return GrpName;
    }

    public void setGrpName(String grpName) {
        GrpName = grpName;
    }

    public Float getContributionAmount() {
        return contributionAmount;
    }

    public void setContributionAmount(Float contributionAmount) {
        this.contributionAmount = contributionAmount;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
}
