package org.spring.authenticationservice.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "group_table")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String GrpName;

    @Column(nullable = false)
    private Float contributionAmount;

    @Column(nullable = false)
    private Integer memberLimit = 0;

    @Column(nullable = false)
    private Long adminId; // Linking to the admin (User)

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private List<UserGroupRole> userGroupRoles;

    @Column(nullable = false)
    private Integer currentCycle = 0;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_groups",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> members = new ArrayList<>(); // ✅ Initialize the list



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGrpName() {
        return GrpName;
    }

    public void setGrpName(String grpName) {
        GrpName = grpName;
    }

    public Float getContributionAmount() {
        return this.contributionAmount;
    }

    public void setContributionAmount(Float contributionAmount) {
        this.contributionAmount = contributionAmount;
    }

    public Integer getMemberLimit() {
        return memberLimit;
    }

    public void setMemberLimit(Integer memberLimit) {
        this.memberLimit = memberLimit;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public List<UserGroupRole> getUserGroupRoles() {
        return userGroupRoles;
    }

    public void setUserGroupRoles(List<UserGroupRole> userGroupRoles) {
        this.userGroupRoles = userGroupRoles;
    }

    public Integer getCurrentCycle() {
        return currentCycle;
    }

    public void setCurrentCycle(Integer currentCycle) {
        this.currentCycle = currentCycle;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }
}
