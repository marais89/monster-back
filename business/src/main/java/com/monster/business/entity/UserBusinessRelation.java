package com.monster.business.entity;

import javax.persistence.*;

@Entity
@Table(name = "userBusinessRelations")
public class UserBusinessRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "id_individu")
    private int individuId;
    @Column(name = "id_business")
    private int businessId;
    @Column(name = "id_group")
    private int groupId;
    @Column(name = "role")
    private UserBusinessRole role;
    @Column(name = "status")
    private UserBusinessStatus status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIndividuId() {
        return individuId;
    }

    public void setIndividuId(int individuId) {
        this.individuId = individuId;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public UserBusinessRole getRole() {
        return role;
    }

    public void setRole(UserBusinessRole role) {
        this.role = role;
    }

    public UserBusinessStatus getStatus() {
        return status;
    }

    public void setStatus(UserBusinessStatus status) {
        this.status = status;
    }
}
