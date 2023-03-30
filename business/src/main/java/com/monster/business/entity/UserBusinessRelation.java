package com.monster.business.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "userBusinessRelations")
public class UserBusinessRelation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "id_individu")
    private int individuId;
    @Column(name = "id_business")
    private int businessId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(insertable = true, updatable = true, name = "business_id")
    private Business business;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(insertable = true, updatable = true, name = "group_id")
    private BusinessGroup businessGroup;

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

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public BusinessGroup getBusinessGroup() {
        return businessGroup;
    }

    public void setBusinessGroup(BusinessGroup businessGroup) {
        this.businessGroup = businessGroup;
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
